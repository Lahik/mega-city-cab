package com.megacitycab.validation;

public class SanitizationValidator implements InputValidator {
    @Override
    public boolean validate(String input) {
        String sanitized = input.replaceAll("<script.*?>.*?</script>", "");
        return sanitized.equals(input);
    }

    @Override
    public String getErrorMessage() {
        return "Input contains potentially harmful script content.";
    }
}
