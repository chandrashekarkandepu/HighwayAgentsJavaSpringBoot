package com.highwayagents.highway.agents.controllers;

import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Contractor;
import com.highwayagents.highway.agents.services.ContractorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",methods = {RequestMethod.HEAD,RequestMethod.GET,RequestMethod.POST})
@RequestMapping("api/v1/contractor")
@PreAuthorize("hasRole('CONTRACTOR')")
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
    @GetMapping("/getNameByEmailId")
    public ResponseEntity<String> getContractorName(@RequestParam("emailId") String emailId ){
        return ResponseEntity.ok(contractorService.getContractorNameByEmailId(emailId));

    }

    @GetMapping("/getAllContractors")
    public List<Contractor> getAllContractors(){
        List<Contractor> list=contractorService.getAllContractors();
        return list;
    }

    @GetMapping("/dummy")
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("Contractor from Secured URL");
    }



}
