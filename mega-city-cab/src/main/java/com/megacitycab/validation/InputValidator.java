package com.megacitycab.validation;

public interface InputValidator {
	boolean validate(String input);
	String getErrorMessage();
}
