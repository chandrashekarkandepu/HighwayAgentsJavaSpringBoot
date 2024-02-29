package com.highwayagents.highway.agents.repositories;

import com.highwayagents.highway.agents.models.Contractor;
import com.highwayagents.highway.agents.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    Optional<Worker> findOneByEmailIdAndPassword(String emailId, String password);
    Worker findByEmailId(String emailId);

    List<Worker> findWorkerByCity(String city);

    List<Worker> findWorkerByState(String state);

    List<Worker> findWorkerByStateAndWorkerType(String state,String workerType);
}
