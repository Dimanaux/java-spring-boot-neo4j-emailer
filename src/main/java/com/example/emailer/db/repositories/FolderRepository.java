package com.example.emailer.db.repositories;

import com.example.emailer.db.entities.Folder;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolderRepository extends Neo4jRepository<Folder, Long> {
    @Query("MATCH (f:Folder {folderId: {folderId}}) " +
            " MATCH (a:Account {accountId: {accountId}}) " +
            " CREATE (a)-[:OWNS]->(f) " +
            " CREATE (f)-[:OWNED_BY]->(a);")
    void bindToAccount(@Param("accountId") String accountId,
                       @Param("folderId") long folderId);

    @Query("MATCH (a:Account {accountId: {accountId}})-[:OWNS]->(f:Folder {name: {folderName}})-[:CONTAINS]->(m:Message)-->(p) RETURN a, f, m, p;")
    Optional<Folder> findByAccountAndName(@Param("accountId") String accountId,
                                          @Param("folderName") String folderName);

    @Query("MATCH (a:Account {accountId: {accountId}})-[:OWNS]->(f:Folder {name: {folderName}}) RETURN a, f;")
    Optional<Folder> findEmptyByAccountAndName(@Param("accountId") String accountId,
                                               @Param("folderName") String folderName);

    @Query("MATCH (f:Folder {folderId: {folderId}}) MATCH (m:Message {messageId: {messageId}}) CREATE (f)-[:CONTAINS]->(m);")
    void bindFolderAndMessage(@Param("folderId") long folderId,
                              @Param("messageId") long messageId);

}
