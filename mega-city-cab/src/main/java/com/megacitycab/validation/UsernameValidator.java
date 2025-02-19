package com.megacitycab.validation;

public class UsernameValidator implements InputValidator {
    @Override
    public boolean validate(String input) {
        return input.matches("^[A-Za-z0-9_]+$");
    }

    @Override
    public String getErrorMessage() {
        return "Username can only contain letters, numbers and underscores";
    }
}

