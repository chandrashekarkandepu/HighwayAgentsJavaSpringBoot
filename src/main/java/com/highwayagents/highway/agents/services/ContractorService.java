package com.highwayagents.highway.agents.services;

import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Contractor;

public interface ContractorService {
    Contractor createContractor(Contractor contractors);


    String loginContractor(LoginDTO loginDTO);



}
