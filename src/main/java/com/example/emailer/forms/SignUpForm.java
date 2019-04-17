package com.example.emailer.forms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpForm {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
