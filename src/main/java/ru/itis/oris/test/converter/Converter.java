package ru.itis.oris.test.converter;

public interface Converter<S, T> {
    T convert (S s);
}
