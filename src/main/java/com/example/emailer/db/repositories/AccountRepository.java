package com.example.emailer.db.repositories;

import com.example.emailer.db.entities.Account;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends Neo4jRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    List<Account> findAllByFirstNameAndLastName(String firstName, String lastName);

    @Query("MATCH (a:Account {accountId: {id}}) SET a.signature = {signature};")
    void signature(@Param("id") String accountId, @Param("signature") String signature);
}
