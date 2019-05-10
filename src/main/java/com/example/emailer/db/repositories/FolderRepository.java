package com.example.emailer.db.repositories;

import com.example.emailer.db.entities.Folder;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends Neo4jRepository<Folder, Long> {
}
