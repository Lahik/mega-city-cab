package com.megacitycab.validation;

public class SeatsValidator implements InputValidator {
    @Override
    public boolean validate(String input) {
        try {
            int seats = Integer.parseInt(input);
            return seats > 0 && seats <= 12;  
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return "Number of seats must be between 1 and 12";
    }
}
