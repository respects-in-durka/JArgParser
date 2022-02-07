package com.nikitka.jargparser.exception;

public class ConfigInstantiationException extends RuntimeException {
    public ConfigInstantiationException(Class<?> clazz, Exception parent) {
        super(String.format("%s instantiation exception: %s: %s", clazz, parent, parent.getMessage().equals("") ? parent.getCause().getMessage() : parent.getMessage()));
    }
}
