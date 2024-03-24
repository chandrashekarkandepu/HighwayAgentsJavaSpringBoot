package com.highwayagents.highway.agents.controllers;

import com.highwayagents.highway.agents.models.Contractor;
import com.highwayagents.highway.agents.services.ContractorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    private final ContractorService contractorService;

    public DemoController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @GetMapping("/demo")
    public ResponseEntity<String> demo(){
        return ResponseEntity.ok("Hello from Secured URL");
    }

    @GetMapping("/getAllContractors")
    public ResponseEntity<List<Contractor>> getAllContractors(){
        return ResponseEntity.ok(contractorService.getAllContractors());
    }
}
