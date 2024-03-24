package com.highwayagents.highway.agents.controllers;

import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Worker;
import com.highwayagents.highway.agents.services.WorkerService;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/worker")
@CrossOrigin(origins = "http://localhost:3000")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping("/register")
    public Worker saveWorker(@RequestBody Worker worker){
        Worker savedWorker=workerService.createWorker(worker);
        return savedWorker;
    }
    @PostMapping("worker/login")
    public String loginWorker(@RequestBody LoginDTO loginDTO){
        String message= workerService.loginWorker(loginDTO);
        return message;
    }
    @GetMapping("worker/getAllWorkers")
    public List<Worker> getAllWorkers(){
       List<Worker> list= workerService.getAllWorkers();
       return list;
    }

    @GetMapping("/byCity/{city}")
    public List<Worker> getAllWorkersByLocation(@PathVariable String city){
        List<Worker> list=workerService.getWorkerByCity(city);
        return  list;
    }

    public List<Worker> getAllWorkerByState(@PathVariable String state){
        List<Worker> list=workerService.getWorkerByState(state);
        return list;
    }

    @GetMapping("worker/getWorkersByStateAndType")
    public List<Worker> getWorkersByStateAndType(@RequestParam String state,@RequestParam String workerType){
        return workerService.getWorkersByStateAndWorkerType(state,workerType);
    }


}
