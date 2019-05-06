package com.example.emailer.db.entities.id;

import org.neo4j.ogm.id.IdStrategy;

public class AccountIdStrategy extends AbstractSequenceBasedIdStrategy implements IdStrategy {
    @Override
    String sequenceName() {
        return "account_id_sequence";
    }
}
