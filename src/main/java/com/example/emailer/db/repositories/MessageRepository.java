package com.example.emailer.db.repositories;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends Neo4jRepository<Message, Long> {
    @Query("MATCH (sender:Account {email: {account_email} })-[by]-(m: Message)-[to:SENT_TO]->(r:Account) RETURN sender, m, by, to, r;")
    List<Message> findAllAvailableToAccount(@Param("account_email") String accountEmail);

    List<Message> findAllBySender(Account account);

    @Query("MATCH (a: Account)-[r]->(m: Message {status: 'DRAFT'}) RETURN m, r, a;")
    List<Message> findAllDrafts(Account account);

    @Query("MATCH (a: Account)-[r:RECEIVED]->(m: Message) RETURN m, r, a;")
    List<Message> findAllReceivedByAccount(Account account);
}
