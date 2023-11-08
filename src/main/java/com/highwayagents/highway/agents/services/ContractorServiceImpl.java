package com.highwayagents.highway.agents.services;

import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Contractor;
import com.highwayagents.highway.agents.repositories.ContractorRepository;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractorServiceImpl implements ContractorService{

    @Autowired
    private  ContractorRepository contractorRepository;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }



    @Override
    public Contractor createContractor(Contractor contractor) {
        Contractor contractorEntity=new Contractor();
        contractor.setPassword(this.passwordEncoder().encode(contractor.getPassword()));
        BeanUtils.copyProperties(contractor,contractorEntity);
        contractorRepository.save(contractorEntity);
        return contractor;
    }



    @Override
    public String loginContractor(LoginDTO loginDTO) {
        String msg="";
        Contractor contractor1=contractorRepository.findByEmailId(loginDTO.getEmailId());
        if(contractor1!=null){
            String password=loginDTO.getPassword();
            String encodedPassword=contractor1.getPassword();
            Boolean isPwdRight=passwordEncoder().matches(password,encodedPassword);
            if(isPwdRight){
                Optional<Contractor> contractor2=contractorRepository.findOneByEmailIdAndPassword(loginDTO.getEmailId(),encodedPassword);
                if(contractor2.isPresent()){
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


}
