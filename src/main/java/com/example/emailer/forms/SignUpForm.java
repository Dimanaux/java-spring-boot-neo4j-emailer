package com.example.emailer.forms;

public class SignUpForm {
    private String email;

    private String firstName;
    private String lastName;

    private String password;

    public SignUpForm() {
    }

    public String getEmail() {
        return email;
    }

    public SignUpForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public SignUpForm setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SignUpForm setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SignUpForm setPassword(String password) {
        this.password = password;
        return this;
    }
}
