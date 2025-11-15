package ru.itis.oris.test.util.validator;

public class PasswordValidator extends RegexValidator {
    public PasswordValidator() {
        super("^.{8,255}$");
    }
}
