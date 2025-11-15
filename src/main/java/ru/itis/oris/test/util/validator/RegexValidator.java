package ru.itis.oris.test.util.validator;

import java.util.regex.Pattern;

public class RegexValidator implements Validator<String> {
    private final Pattern pattern;

    public RegexValidator(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public boolean validate(String input) {
        if (input == null) {
            return false;
        }
        return pattern.matcher(input).matches();
    }
}
