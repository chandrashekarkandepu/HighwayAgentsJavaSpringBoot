package com.highwayagents.highway.agents.controllers;

import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Agency;
import com.highwayagents.highway.agents.models.Contractor;
import com.highwayagents.highway.agents.services.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "https://highwayagents.azurewebsites.net,http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class AgencyController {

    @Autowired
    private  AgencyService agencyService;


    @GetMapping("/getAllAgencies")
    public List<Agency> getAllAgencies(){
        List<Agency> list=agencyService.getAllAgencies();
        return list;
    }
    @PostMapping("agencies/register")
    public Agency createAgency(@RequestBody Agency agency){
        return agencyService.createAgency(agency);
    }
    @PostMapping("agencies/login")
    public String loginAgency(@RequestBody LoginDTO loginDTO){
        String message= agencyService.loginAgency(loginDTO);
        return message;
    }

    @GetMapping("agenciesByCity/{city}")
    public  List<Agency> getAllAgenciesByCity(@PathVariable String city){
        List<Agency> listOfAllAgenciesByCity=agencyService.getAllAgenciesByCity(city);
        return listOfAllAgenciesByCity;

    }

    @GetMapping("agenciesByState/{state}")
    public  List<Agency> getAllAgenciesByState(@PathVariable String state){
        List<Agency> listOfAllAgenciesByState=agencyService.getAllAgenciesByState(state);
        return listOfAllAgenciesByState;
    }

    @GetMapping("getAgenciesByStateAndType")
    public List<Agency> getAllAgenciesByStateAndByType(@RequestParam String state,@RequestParam String agencyType){
        return agencyService.findAgenciesByStateAndTypeOfAgency(state,agencyType);
    }
}
