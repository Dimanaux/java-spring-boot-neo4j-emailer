package com.example.emailer.services.functions;

import com.example.emailer.db.entities.Account;

@FunctionalInterface
public interface Creator {
    void by(Account account);
}
