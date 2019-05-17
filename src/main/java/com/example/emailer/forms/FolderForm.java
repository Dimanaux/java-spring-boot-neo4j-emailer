package com.example.emailer.forms;

import com.example.emailer.forms.validation.LatinOnly;

public class FolderForm {
    @LatinOnly
    private String name;

    public FolderForm() {
    }

    public FolderForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public FolderForm setName(String name) {
        this.name = name;
        return this;
    }
}
