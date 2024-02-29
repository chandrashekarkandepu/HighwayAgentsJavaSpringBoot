package com.highwayagents.highway.agents.repositories;

import com.highwayagents.highway.agents.models.Agency;
import com.highwayagents.highway.agents.models.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository

public interface AgencyRepository extends JpaRepository<Agency, Integer> {
    Optional<Agency> findOneByEmailIdAndPassword(String emailId, String password);
    Agency findByEmailId(String emailId);

    List<Agency> findAgencyByCity(String city);

    List<Agency> findAgencyByState(String state);

    List<Agency> findAgenciesByStateAndAgencyType(String state,String agencyType);

}
