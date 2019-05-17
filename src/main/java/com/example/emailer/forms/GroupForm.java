package com.example.emailer.forms;

import com.example.emailer.forms.validation.LatinOnly;

public class GroupForm {
    @LatinOnly
    private String name;

    public GroupForm() {
    }

    public GroupForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public GroupForm setName(String name) {
        this.name = name;
        return this;
    }
}
