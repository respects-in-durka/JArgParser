package com.nikitka.jargparser.exception;

public class NoDefaultConstructorException extends RuntimeException {

    public NoDefaultConstructorException(Class<?> clazz) {
        super(String.format("No default contructor found in class %s", clazz));
    }
}
