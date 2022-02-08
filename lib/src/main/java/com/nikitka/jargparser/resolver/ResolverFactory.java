package com.nikitka.jargparser.resolver;

import java.util.List;
import com.nikitka.jargparser.resolver.impl.ListResolver;

public class ResolverFactory {

    public static Resolver<?> getResolver(Class<?> clazz) {
        if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
            return (input -> Integer.parseInt(input));
        } else if (clazz.equals(String.class)) {
            return (input -> input);
        } else if (clazz.equals(Byte.class) || clazz.equals(byte.class)) {
            return (input -> Byte.parseByte(input));
        } else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
            return (input -> Short.parseShort(input));
        } else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
            return (input -> Long.parseLong(input));
        } else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
            return (input -> Float.parseFloat(input));
        } else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
            return (input -> Double.parseDouble(input));
        } else if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
            return (input -> Boolean.parseBoolean(input));
        } else if (clazz.equals(Character.class) || clazz.equals(char.class)) {
            return (input -> Character.valueOf(input.charAt(0)));
        } else if (List.class.isAssignableFrom(clazz)) {
            return new ListResolver();
        }
        return (input -> input);
    }
    
}
