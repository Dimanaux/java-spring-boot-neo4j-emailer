package com.example.emailer.db.repositories;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends Neo4jRepository<Message, Long> {
    @Query("MATCH (a:Account {email: {account_email} })-->(m: Message) RETURN m;")
    List<Message> findAllAvailableToAccount(@Param("account_email") String accountEmail);

    List<Message> findAllBySender(Account account);

    @Query("MATCH (a: Account)-[:SENT]->(m: Message {status: 'DRAFT'}) RETURN m;")
    List<Message> findAllDrafts(Account account);

    @Query("MATCH (a: Account)-[:RECEIVED]->(m: Message) RETURN m;")
    List<Message> findAllReceivedByAccount(Account account);
}
