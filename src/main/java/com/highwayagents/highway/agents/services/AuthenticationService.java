package com.highwayagents.highway.agents.services;

import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Agency;
import com.highwayagents.highway.agents.models.AuthenticationResponse;
import com.highwayagents.highway.agents.models.Contractor;
import com.highwayagents.highway.agents.models.Token;
import com.highwayagents.highway.agents.repositories.AgencyRepository;
import com.highwayagents.highway.agents.repositories.ContractorRepository;
import com.highwayagents.highway.agents.repositories.TokenRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private final ContractorRepository repository;

    private final AgencyRepository agencyRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;

    public AuthenticationService(ContractorRepository repository, AgencyRepository agencyRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, TokenRepository tokenRepository) {
        this.repository = repository;
        this.agencyRepository = agencyRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }

    public AuthenticationResponse register(Contractor request){
        Contractor contractor = new Contractor();
        contractor.setContractorName(request.getContractorName());
        contractor.setEmailId(request.getEmailId());
        contractor.setPassword(passwordEncoder.encode(request.getPassword()));
        contractor.setPhoneNumber(request.getPhoneNumber());
        contractor.setCity(request.getCity());
        contractor.setState(request.getState());
        contractor.setCountry(request.getCountry());
        contractor.setRole(request.getRole());

        contractor = repository.save(contractor);

        String jwt = jwtService.generateToken(contractor);

        saveUserToken(contractor, jwt);


        return new AuthenticationResponse(jwt);
    }

    private void saveUserToken(Contractor contractor, String jwt) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setContractor(contractor);
        tokenRepository.save(token);
    }

    public AuthenticationResponse authenticate(LoginDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailId(),
                        request.getPassword()
                )
        );
                Contractor contractor = repository.findByEmailId(request.getEmailId());
                String token = jwtService.generateToken(contractor);

        revokeAllTokensByUser(contractor);
        saveUserToken(contractor,token);
        return new AuthenticationResponse(token);

    }

    private void revokeAllTokensByUser(Contractor contractor) {
        List<Token> validTokenByUser = tokenRepository.findAllByTokenUser(contractor.getContractorId());
        if(!validTokenByUser.isEmpty()){
            validTokenByUser.forEach(
                    t->{
                        t.setLoggedOut(true);
                    }
            );
        }
        tokenRepository.saveAll(validTokenByUser);
    }


}
