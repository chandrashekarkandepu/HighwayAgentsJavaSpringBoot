package com.highwayagents.highway.agents.services;


import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Agency;
import com.highwayagents.highway.agents.repositories.AgencyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private  AgencyRepository agencyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;




    @Override
    public List<Agency> getAllAgencies() {
        List<Agency> listAgency=agencyRepository.findAll();
        return listAgency;
    }

    @Override
    public Agency createAgency(Agency agency) {
        Agency agencyEntity=new Agency();
        agency.setPassword(this.passwordEncoder.encode(agency.getPassword()));
        BeanUtils.copyProperties(agency,agencyEntity);
        agencyRepository.save(agencyEntity);
        return agency;
    }

    @Override
    public String loginAgency(LoginDTO loginDTO) {
        String msg="";
        Agency agency1=agencyRepository.findByEmailId(loginDTO.getEmailId());
        if(agency1!=null){
            String password=loginDTO.getPassword();
            System.out.println(password);
            String encodedPassword=agency1.getPassword();
            System.out.println(encodedPassword);
            Boolean isPwdRight=this.passwordEncoder.matches(password,encodedPassword);
            System.out.print(isPwdRight);
            if(isPwdRight){
                Optional<Agency> agency2=agencyRepository.findOneByEmailIdAndPassword(loginDTO.getEmailId(),encodedPassword);
                if(agency2.isPresent()){
                    return "Login Success";
                }
                else{
                    return "Login Failed";
                }
            }
            else{
                return "Password Not Match";
            }
        }
        else{
            return "Email Not Exists";
        }
    }

    @Override
    public List<Agency> getAllAgenciesByCity(String city) {
        List<Agency> listOfAgenciesByCity=agencyRepository.findAgencyByCity(city);
        return  listOfAgenciesByCity;
    }

    @Override
    public List<Agency> getAllAgenciesByState(String state) {
        List<Agency> listOfAgenciesByState=agencyRepository.findAgencyByState(state);
        return  listOfAgenciesByState;
    }

    @Override
    public List<Agency> findAgenciesByStateAndTypeOfAgency(String state, String agencyType) {
        List<Agency> listOfAllAgenciesByStateAndType=agencyRepository.findAgenciesByStateAndAgencyType(state,agencyType);
        return  listOfAllAgenciesByStateAndType;
    }


}
