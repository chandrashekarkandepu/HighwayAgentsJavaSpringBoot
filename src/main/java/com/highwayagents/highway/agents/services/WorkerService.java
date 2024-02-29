package com.highwayagents.highway.agents.services;

import com.highwayagents.highway.agents.DTO.LoginDTO;
import com.highwayagents.highway.agents.models.Worker;

import java.util.List;

public interface WorkerService {
    List<Worker> getAllWorkers();
    Worker createWorker(Worker worker);

    String loginWorker(LoginDTO loginDTO);

    List<Worker> getWorkerByCity(String city);

    List<Worker> getWorkerByState(String state);

    List<Worker> getWorkersByStateAndWorkerType(String state,String workerType);
}
