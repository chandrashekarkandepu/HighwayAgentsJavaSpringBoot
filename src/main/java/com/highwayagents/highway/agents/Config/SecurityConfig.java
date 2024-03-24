package com.highwayagents.highway.agents.Config;

import com.highwayagents.highway.agents.filter.JwtAuthenticationFilter;
import com.highwayagents.highway.agents.services.AgencyServiceImpl;
import com.highwayagents.highway.agents.services.ContractorServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity

@AllArgsConstructor
public class SecurityConfig {

    private final ContractorServiceImpl contractorServiceImpl;

    private final AgencyServiceImpl agencyServiceImpl;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomLogoutHandler logoutHandler;

    private final CustomAccessHandler accessHandler;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


        @Bean
        public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            return http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(
                            req->req.requestMatchers("/login/**","/register/**")
                                    .permitAll()
                                    .requestMatchers("/contractor/**").hasRole("CONTRACTOR")
                                    .requestMatchers("/agency/**").hasRole("AGENCY")
                                    .requestMatchers("/worker/**").hasRole("WORKER")
                                    .anyRequest()
                                    .authenticated()
                    ).userDetailsService(contractorServiceImpl)
                    .exceptionHandling(e->e.accessDeniedHandler(
                                    ((request, response, accessDeniedException) -> {
                                        response.setStatus(403);
                                    })
                            )
                            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                    .sessionManagement(session->    session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .logout(l->l.logoutUrl("/logout")
                            .addLogoutHandler(logoutHandler)
                            .logoutSuccessHandler(
                                    ((request, response, authentication) -> SecurityContextHolder.clearContext())
                            )
                    )
                    .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
                    .build();
        }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // Allow requests from any origin
        configuration.addAllowedMethod("*"); // Allow all HTTP methods
        configuration.addAllowedHeader("*"); // Allow all headers
        //configuration.setAllowCredentials(true); // Allow sending cookies
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration to all paths
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }


//    public UserDetailsService userDetailsService(){
//        return username -> {
//            if(contractorServiceImpl.loadUserByUsername(username)!=null){
//                return contractorServiceImpl.loadUserByUsername(username);
//            }
//            else{
//                return agencyServiceImpl.loadAgencyByUsername()
//            }
//        }
//    }



}
