package com.highwayagents.highway.agents.services;

import com.highwayagents.highway.agents.Config.SecurityConfig;
import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Contractor;
import com.highwayagents.highway.agents.repositories.ContractorRepository;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractorServiceImpl implements ContractorService, UserDetailsService {

    @Autowired
    private  ContractorRepository contractorRepository;


    private PasswordEncoder passwordEncoder;

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public Contractor createContractor(Contractor contractor) {
        Contractor contractorEntity=new Contractor();
        contractor.setPassword(passwordEncoder.encode(contractor.getPassword()));
        BeanUtils.copyProperties(contractor,contractorEntity);
        contractorRepository.save(contractorEntity);
        return contractor;
    }



    @Override
    public String loginContractor(LoginDTO loginDTO) {
        String msg="";
        Contractor contractor1=contractorRepository.findByEmailId(loginDTO.getEmailId());
        if(contractor1!=null){
            String password=loginDTO.getPassword().trim();
            String encodedPassword=contractor1.getPassword().trim();
            Boolean isPwdRight=this.passwordEncoder.matches(password,encodedPassword);
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

    @Override
    public String getContractorNameByEmailId(String emailId) {
       Contractor contractor=contractorRepository.findByEmailId(emailId);
       return contractor.getContractorName();
    }

    @Override
    public List<Contractor> getAllContractors() {
        return contractorRepository.findAll();
    }


    @Override
    public Contractor loadUserByUsername(String username) throws UsernameNotFoundException {
        return contractorRepository.findByEmailId(username);
    }
}
