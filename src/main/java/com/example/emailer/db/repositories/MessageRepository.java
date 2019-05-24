package com.example.emailer.db.repositories;

import com.example.emailer.db.entities.Message;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends Neo4jRepository<Message, Long> {
    @Query("MATCH (a:Account {accountId: {accountId}})-[p:HAS]->(m:Message {status: 'SENT'})-[r]-(i) RETURN a, m, p, r, i;")
    List<Message> findAllBySender(@Param("accountId") String accountId);

    @Query("MATCH (a:Account {accountId: {accountId}})-[p:HAS]->(m:Message {status: {status}})-[r]-(i) RETURN a, m, p, r, i;")
    List<Message> findAllBySenderAndStatus(@Param("accountId") String accountId,
                                           @Param("status") String status);

    @Query("MATCH (a: Account {accountId: {accountId}})-[p:HAS]->(m: Message {messageId: {messageId}}) DELETE p;")
    void deleteMessageFromAccount(@Param("accountId") String accountId,
                                  @Param("messageId") long messageId);

    @Query("MATCH (a:Account {accountId: {accountId}}) MATCH (m:Message {messageId: {messageId}}) CREATE (a)-[r:HAS]->(m);")
    void addMessageToAccount(@Param("accountId") String accountId,
                             @Param("messageId") long messageId);

    @Query("MATCH (a:Account {accountId: {accountId}})-[p:HAS]->(m:Message)-[r]-(s) RETURN a, p, m, r, s;")
    List<Message> findAllByAccount(@Param("accountId") String accountId);

    @Query("MATCH  (a:Account {accountId: {accountId}}) MATCH (m:Message {messageId: {messageId}}) RETURN exists((a)-[:HAS]->(m));")
    boolean accountHasMessage(@Param("accountId") String accountId,
                              @Param("messageId") long messageId);

    @Query("MATCH (f:Folder {folderId: {folderId}})-[c:CONTAINS]->(m:Message)-[r]-(a) RETURN f, c, m, r, a;")
    List<Message> findByFolder(@Param("folderId") long folderId);

    @Query("MATCH (a:Account {accountId: {accountId}}) MATCH (m:Message {messageId: {messageId}}) CREATE (a)<-[:SENT_BY]-(m);")
    void setSender(@Param("accountId") String accountId,
                   @Param("messageId") long messageId);

    @Query("MATCH (a:Account {accountId: {accountId}}) MATCH (m:Message {messageId: {messageId}}) CREATE (a)<-[:SENT_TO]-(m);")
    void setRecipient(@Param("accountId") String accountId,
                      @Param("messageId") Long messageId);

    @Query("MATCH (a:Account {accountId: {accountId}}) MATCH (m:Message {messageId: {messageId}}) CREATE (a)<-[:COPIED_TO]-(m);")
    void setCopyRecipient(@Param("accountId") String accountId,
                          @Param("messageId") Long messageId);

    @Query("MATCH (a:Account {accountId: {accountId}}) MATCH (m:Message {messageId: {messageId}}) CREATE (a)<-[:SECRETLY_COPIED_TO]-(m);")
    void setSecretCopyRecipient(@Param("accountId") String accountId,
                                @Param("messageId") Long messageId);
}
