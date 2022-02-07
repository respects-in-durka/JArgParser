package com.nikitka.jargparser.resolver;

@FunctionalInterface
public interface Resolver<T> {
    public T resolve(String input);
}
