package com.highwayagents.highway.agents.controllers;

import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Agency;
import com.highwayagents.highway.agents.models.AuthenticationResponse;
import com.highwayagents.highway.agents.models.Contractor;
import com.highwayagents.highway.agents.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private  final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Contractor request
            ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginDTO request
    ){
        return  ResponseEntity.ok(authenticationService.authenticate(request));
    }

//    @PostMapping("/register/agency")
//
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestBody Agency request
//    ){
//        return ResponseEntity.ok(authenticationService.registerAgency(request));
//    }

//    @PostMapping("/login/agency")
//    public ResponseEntity<AuthenticationResponse> agencyLogin(
//            @RequestBody LoginDTO request
//    ){
//        return  ResponseEntity.ok(authenticationService.authenticateAgency(request));
//    }

}
