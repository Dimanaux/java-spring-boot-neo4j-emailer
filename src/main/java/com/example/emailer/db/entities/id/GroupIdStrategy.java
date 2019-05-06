package com.example.emailer.db.entities.id;

import org.neo4j.ogm.id.IdStrategy;

public class GroupIdStrategy extends AbstractSequenceBasedIdStrategy implements IdStrategy {
    @Override
    String sequenceName() {
        return "group_id_sequence";
    }
}
