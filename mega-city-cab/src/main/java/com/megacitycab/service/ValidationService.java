package com.megacitycab.service;

import java.util.HashMap;
import java.util.Map;

import com.megacitycab.validation.InputValidator;
import com.megacitycab.validation.NicValidator;
import com.megacitycab.validation.PasswordValidator;
import com.megacitycab.validation.SanitizationValidator;
import com.megacitycab.validation.TelephoneValidator;
import com.megacitycab.validation.UsernameValidator;

public class ValidationService {
    private Map<String, InputValidator> validators = new HashMap<>();

    public ValidationService() {
        validators.put("nic", new NicValidator());
        validators.put("telephone", new TelephoneValidator());
        validators.put("username", new UsernameValidator());
        validators.put("password", new PasswordValidator());
        validators.put("sanitization", new SanitizationValidator());
    }

    public boolean validate(String field, String input, String confirmPassword) {
        InputValidator validator = validators.get(field);
        if (validator != null) {
            return validator.validate(input);
        }
        return false;
    }

    public String getErrorMessage(String field) {
        InputValidator validator = validators.get(field);
        if (validator != null) {
            return validator.getErrorMessage();
        }
        return "Invalid input.";
    }
}
