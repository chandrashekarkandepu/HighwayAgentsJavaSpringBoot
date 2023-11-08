package com.highwayagents.highway.agents.controllers;

import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Contractor;
import com.highwayagents.highway.agents.services.ContractorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/contractor")
public class ContractorController {

    private final ContractorService contractorService;

    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @PostMapping("/register")
    public Contractor createContractor(@RequestBody Contractor contractor){
        return contractorService.createContractor(contractor);
    }
    @PostMapping("/login")
    public String loginContractor(@RequestBody LoginDTO loginDTO){
       String message= contractorService.loginContractor(loginDTO);
       return message;
    }

}
