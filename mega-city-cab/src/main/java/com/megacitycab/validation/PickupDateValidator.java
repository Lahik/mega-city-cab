package com.megacitycab.validation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PickupDateValidator implements InputValidator {

    @Override
    public boolean validate(String input) {
        try {
            LocalDate pickupDate = LocalDate.parse(input);
            ZonedDateTime currentDateTimeSL = ZonedDateTime.now(ZoneId.of("Asia/Colombo"));
            LocalDate currentDateSL = currentDateTimeSL.toLocalDate();
            
            return !pickupDate.isBefore(currentDateSL); 
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return "Invalid Date";
    }
}
