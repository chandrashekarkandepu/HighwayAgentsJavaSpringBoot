package com.highwayagents.highway.agents.filter;

import com.highwayagents.highway.agents.models.Contractor;
import com.highwayagents.highway.agents.services.AgencyServiceImpl;
import com.highwayagents.highway.agents.services.ContractorServiceImpl;
import com.highwayagents.highway.agents.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final ContractorServiceImpl contractorService;

    private final AgencyServiceImpl agencyService;


    public JwtAuthenticationFilter(JwtService jwtService, ContractorServiceImpl contractorService, AgencyServiceImpl agencyService) {
        this.jwtService = jwtService;
        this.contractorService = contractorService;
        this.agencyService = agencyService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);

        String username = jwtService.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Contractor contractorDetails = contractorService.loadUserByUsername(username);

            if (jwtService.isValid(token, contractorDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        contractorDetails, null, contractorDetails.getAuthorities()
                );

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }
        filterChain.doFilter(request, response);

    }

}

