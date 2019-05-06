package com.example.emailer.services.functions;

import com.example.emailer.db.entities.Message;

@FunctionalInterface
public interface MessageReader {
    boolean read(Message message);
}
