package com.megacitycab.validation;

public class PasswordValidator implements InputValidator {
	
    @Override
    public boolean validate(String input) {
    	return input != null && !input.trim().isEmpty();
    }

    @Override
    public String getErrorMessage() {
        return "Password and confirm password must match.";
    }
    
    public boolean confirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}