package com.megacitycab.service;

import java.util.HashMap;
import java.util.Map;

import com.megacitycab.validation.InputValidator;
import com.megacitycab.validation.NicValidator;
import com.megacitycab.validation.PasswordValidator;
import com.megacitycab.validation.PickupDateValidator;
import com.megacitycab.validation.PickupTimeValidator;
import com.megacitycab.validation.SanitizationValidator;
import com.megacitycab.validation.SeatsValidator;
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
        
        validators.put("pickup_date", new PickupDateValidator());
        validators.put("pickup_time", new PickupTimeValidator());
        validators.put("seats", new SeatsValidator()); 
    }

    public boolean validate(String field, String input) {
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
