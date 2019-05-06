package com.example.emailer.db.entities.id;

import org.neo4j.ogm.id.IdStrategy;

public class FolderIdStrategy extends AbstractSequenceBasedIdStrategy implements IdStrategy {
    @Override
    String sequenceName() {
        return "folder_id_sequence";
    }
}
