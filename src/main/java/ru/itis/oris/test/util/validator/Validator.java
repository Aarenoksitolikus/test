package ru.itis.oris.test.util.validator;

public interface Validator<T> {
    boolean validate(T input);
}
