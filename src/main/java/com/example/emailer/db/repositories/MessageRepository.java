package com.example.emailer.db.repositories;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.entities.Message;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends Neo4jRepository<Message, Long> {
    List<Message> findAllBySender(Account account);

    List<Message> findAllBySenderAndStatus(Account account, String status);

    @Query("MATCH (a: Account {accountId: {accountId}})-[p:HAS]->(m: Message {messageId: {messageId}}) DELETE p;")
    void deleteMessageFromAccount(@Param("accountId") long accountId,
                                  @Param("messageId") long messageId);

    @Query("MATCH (a:Account {accountId: {accountId}}) MATCH (m:Message {messageId: {messageId}}) CREATE (a)-[r:HAS]->(m);")
    void addMessageToAccount(@Param("accountId") long accountId,
                             @Param("messageId") long messageId);

    @Query("MATCH (a:Account {accountId: {accountId}})-[p:HAS]->(m:Message)-[r]-(s) RETURN a, p, m, r, s;")
    List<Message> findAllByAccount(@Param("accountId") long accountId);

    @Query("MATCH  (a:Account {accountId: {accountId}}) MATCH (m:Message {messageId: {messageId}}) RETURN exists((a)-[:HAS]->(m));")
    boolean accountHasMessage(@Param("accountId") long accountId,
                              @Param("messageId") long messageId);

    @Query("MATCH (a:Account {accountId: {accountId}}) MATCH (m:Message {messageId: {messageId}}) CREATE (a)<-[:SENT_BY]-(m);")
    void setSender(@Param("accountId") Long accountId, @Param("messageId") Long messageId);

    @Query("MATCH (a:Account {accountId: {accountId}}) MATCH (m:Message {messageId: {messageId}}) CREATE (a)<-[:SENT_TO]-(m);")
    void setRecipient(@Param("accountId") Long accountId, @Param("messageId") Long messageId);
}
