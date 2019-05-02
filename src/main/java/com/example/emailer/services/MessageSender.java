package com.example.emailer.services;

import com.example.emailer.db.entities.Account;

@FunctionalInterface
public interface MessageSender {
    void by(Account account);
}
