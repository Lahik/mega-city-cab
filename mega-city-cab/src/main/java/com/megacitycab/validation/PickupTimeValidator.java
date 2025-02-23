package com.megacitycab.validation;

import java.time.LocalTime;

public class PickupTimeValidator implements InputValidator {

    @Override
    public boolean validate(String input) {
        try {
            LocalTime pickupTime = LocalTime.parse(input);
            LocalTime currentTime = LocalTime.now();

            if (pickupTime.isBefore(currentTime.plusMinutes(30))) {
                return false; 
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return "Pickup time must be at least 30 minutes ahead";
    }
}
