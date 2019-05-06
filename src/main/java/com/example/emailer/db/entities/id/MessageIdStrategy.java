package com.example.emailer.db.entities.id;

import org.neo4j.ogm.id.IdStrategy;

public class MessageIdStrategy extends AbstractSequenceBasedIdStrategy implements IdStrategy {
    @Override
    String sequenceName() {
        return "message_id_sequence";
    }
}
