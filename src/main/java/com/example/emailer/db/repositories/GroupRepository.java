package com.example.emailer.db.repositories;

import com.example.emailer.db.entities.Group;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GroupRepository extends Neo4jRepository<Group, Long> {
}
