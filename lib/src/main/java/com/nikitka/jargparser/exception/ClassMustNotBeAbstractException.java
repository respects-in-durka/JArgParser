package com.nikitka.jargparser.exception;

public class ClassMustNotBeAbstractException extends RuntimeException {
    public ClassMustNotBeAbstractException(Class<?> clazz) {
        super(String.format("%s must not be abstract", clazz));
    }
}
