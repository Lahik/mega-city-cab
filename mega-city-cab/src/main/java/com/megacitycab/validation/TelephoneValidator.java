package com.megacitycab.validation;

public class TelephoneValidator implements InputValidator {
    @Override
    public boolean validate(String input) {
        if (input.startsWith("07")) {
            return input.length() == 10 && input.matches("07\\d{8}");
        } else if (input.startsWith("7")) {
            return input.length() == 9 && input.matches("7\\d{8}");
        }
        return false;
    }

    @Override
    public String getErrorMessage() {
        return "Invalid phone number";
    }
}
