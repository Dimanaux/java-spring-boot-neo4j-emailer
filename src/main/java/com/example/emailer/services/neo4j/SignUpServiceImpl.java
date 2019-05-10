package com.example.emailer.services.neo4j;

import com.example.emailer.db.entities.Account;
import com.example.emailer.db.repositories.AccountRepository;
import com.example.emailer.forms.SignUpForm;
import com.example.emailer.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpServiceImpl(AccountRepository usersRepository, PasswordEncoder passwordEncoder) {
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
}
