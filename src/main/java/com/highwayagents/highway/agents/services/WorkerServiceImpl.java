package com.highwayagents.highway.agents.services;

import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Worker;
import com.highwayagents.highway.agents.repositories.WorkerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService{
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<Worker> getAllWorkers() {
        List<Worker> listAllWorkers=workerRepository.findAll();
        return listAllWorkers;
    }

    @Override
    public Worker createWorker(Worker worker) {
        Worker workerEntity=new Worker();
        worker.setPassword(this.passwordEncoder.encode(worker.getPassword()));
        BeanUtils.copyProperties(worker,workerEntity);
        workerRepository.save(workerEntity);
        return worker;
    }

    @Override
    public String loginWorker(LoginDTO loginDTO) {
        String msg="";
        Worker worker1=workerRepository.findByEmailId(loginDTO.getEmailId());
        if(worker1!=null){
            String password=loginDTO.getPassword().trim();
            String encodedPassword=worker1.getPassword().trim();
            Boolean isPwdRight=this.passwordEncoder.matches(password,encodedPassword);
            if(isPwdRight){
                Optional<Worker> worker2=workerRepository.findOneByEmailIdAndPassword(loginDTO.getEmailId(),encodedPassword);
                if(worker2.isPresent()){
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
    public List<Worker> getWorkerByCity(String city) {
        List<Worker> getWorkersList=workerRepository.findWorkerByCity(city);
        return getWorkersList;

    }

    @Override
    public List<Worker> getWorkerByState(String state) {
        List<Worker> listOfAllWorkersByState=workerRepository.findWorkerByState(state);
        return listOfAllWorkersByState;
    }

    @Override
    public List<Worker> getWorkersByStateAndWorkerType(String state, String workerType) {
        return workerRepository.findWorkerByStateAndWorkerType(state,workerType);
    }
}
