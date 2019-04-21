package com.example.emailer.db.repositories;

import com.example.emailer.db.entities.Account;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends Neo4jRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    List<Account> findAllByFirstNameAndLastName(String firstName, String lastName);
}
