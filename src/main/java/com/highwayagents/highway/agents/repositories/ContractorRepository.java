package com.highwayagents.highway.agents.repositories;

import com.highwayagents.highway.agents.models.Contractor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Integer> {
    Optional<Contractor> findOneByEmailIdAndPassword(String emailId,String password);
    Contractor findByEmailId(String emailId);


}
