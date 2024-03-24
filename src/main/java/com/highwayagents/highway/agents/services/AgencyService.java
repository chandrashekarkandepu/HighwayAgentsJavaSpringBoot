package com.highwayagents.highway.agents.services;

import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Agency;

import java.util.List;

public interface AgencyService {
    List<Agency> getAllAgencies();

//    Agency createAgency(Agency agency);
//    String loginAgency(LoginDTO loginDTO);
    List<Agency> getAllAgenciesByCity(String city);

    List<Agency> getAllAgenciesByState(String state);

    List<Agency> findAgenciesByStateAndTypeOfAgency(String state,String agencyType);

    Agency loadAgencyByUsername(String username);

}
