package com.example.emailer.services;

import com.example.emailer.db.entities.Account;

@FunctionalInterface
public interface MessageDestroyer {
    void from(Account account);
}
