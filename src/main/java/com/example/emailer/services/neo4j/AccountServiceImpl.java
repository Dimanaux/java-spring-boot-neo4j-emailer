package com.example.emailer.services.neo4j;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.repositories.AccountRepository;
import com.example.emailer.forms.SignUpForm;
import com.example.emailer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(SignUpForm form) {
        Account account = Account.builder()
                .password(passwordEncoder.encode(form.getPassword()))
                .email(form.getEmail())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .build();
        accountRepository.save(account);
    }

    @Override
    public Consumer<Account> signature(String signature) {
        return account -> {
            account.setSignature(signature);
            accountRepository.signature(account.getAccountId(), signature);
        };
    }
}
