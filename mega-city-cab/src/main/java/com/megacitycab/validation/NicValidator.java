package com.megacitycab.validation;

public class NicValidator implements InputValidator {

	@Override
	public boolean validate(String input) {
		return input.matches("\\d{12}|\\d{9}");
	}

	@Override
	public String getErrorMessage() {
		return "NIC must be 12 digits or 9 digits";
	}
	
}
