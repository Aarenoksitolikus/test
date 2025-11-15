package ru.itis.oris.test.util.validator;

public class UsernameValidator extends RegexValidator {
    public UsernameValidator() {
        super("^[a-zA-Z0-9_]{1,50}$");
    }

    @Override
    public boolean validate(String input) {
        return super.validate(input == null ? input : input.trim());
    }
}
