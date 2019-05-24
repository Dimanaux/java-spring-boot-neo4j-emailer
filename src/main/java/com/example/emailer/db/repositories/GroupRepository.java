package com.example.emailer.db.repositories;

import com.example.emailer.db.entities.Group;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends Neo4jRepository<Group, Long> {
    @Query("MATCH (g:Group {groupId: {groupId}}) MATCH (a:Account {accountId: {accountId}}) CREATE (g)-[:OWNED_BY]->(a);")
    void setOwner(@Param("groupId") Long groupId, @Param("accountId") String accountId);

    @Query("MATCH (g:Group {groupId: {groupId}}) MATCH (a:Account {accountId: {accountId}}) CREATE (g)-[:CONTAINS]->(a) CREATE (a)-[:IN]->(g);")
    void bindAccountToGroup(@Param("groupId") Long groupId, @Param("accountId") String accountId);

    @Query("MATCH (a:Account {accountId: {accountId}})-[:IN]-(g:Group)-[c:CONTAINS]-(p) RETURN a, g, c, p;")
    List<Group> groupsOf(@Param("accountId") String accountId);
}
