package com.highwayagents.highway.agents.repositories;

import com.highwayagents.highway.agents.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {

    @Query(
            """
                    select t from Token t inner join contractor c
                    on t.contractor.id = c.contractorId
                    where t.contractor.id =:contractorId and t.loggedOut = false
                    """

    )
    List<Token> findAllByTokenUser(Integer contractorId);

    Optional<Token> findByToken(String token);

}
