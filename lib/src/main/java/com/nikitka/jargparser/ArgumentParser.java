package com.nikitka.jargparser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.nikitka.jargparser.annotation.Argument;
import com.nikitka.jargparser.config.BaseConfig;
import com.nikitka.jargparser.exception.ClassMustNotBeAbstractException;
import com.nikitka.jargparser.exception.ConfigInstantiationException;
import com.nikitka.jargparser.exception.NoDefaultConstructorException;
import com.nikitka.jargparser.resolver.Resolver;
import com.nikitka.jargparser.resolver.ResolverFactory;

public class ArgumentParser<T extends BaseConfig> {
    private T config;
    private final Map<List<String>, Field> arguments = new HashMap<>();

    @SuppressWarnings("all")
    public ArgumentParser(Class<T> clazz) {
        if (Modifier.isAbstract(clazz.getModifiers())) throw new ClassMustNotBeAbstractException(clazz);
        if (clazz.getDeclaredConstructors().length == 0) throw new RuntimeException("No contructors found");
        Constructor<?> constructor = Arrays.stream(clazz.getDeclaredConstructors()).filter(c -> c.getParameterCount() == 0).findFirst().orElse(null);
        if (constructor == null) throw new NoDefaultConstructorException(clazz);
        if (Modifier.isPrivate(constructor.getModifiers())) constructor.setAccessible(true);
        try {
            this.config = (T) constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new ConfigInstantiationException(clazz, e);
        }
        parseConfig();
    }

    private void parseConfig() {
        List<Field> fields = Arrays.stream(config.getClass().getDeclaredFields()).filter(f -> f.getDeclaredAnnotation(Argument.class) != null).map(f -> {f.setAccessible(true); return f;}).collect(Collectors.toList());
        fields.forEach(f -> {
            Argument arg = f.getDeclaredAnnotation(Argument.class);
            if (arg.aliases().length == 0 && arg.exclude()) throw new IllegalArgumentException(String.format("%s: aliases are empty and exclude set to true", f));
            List<String> aliases = new ArrayList<>(Arrays.asList(arg.aliases()));
            if (!arg.exclude()) aliases.add(f.getName());
            arguments.put(aliases, f);
        });
    }

    public T parse(String[] args) {
        Map<String, String> argumentsMap = Arrays.stream(String.join(" ", args).replace("--", "").split(" ")).map(a -> a.split("=")).collect(Collectors.toMap(arg -> arg[0], arg -> arg[1]));
        this.arguments.forEach((aliases, field) -> {
            Argument data = field.getDeclaredAnnotation(Argument.class);
            argumentsMap.forEach((key, value) -> {
                if (aliases.contains(key)) {
                    Resolver<?> resolver = ResolverFactory.getResolver(field.getType());
                    try {
                        field.set(config, resolver.resolve(value));
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (!data.required()) {

                    } else {

                    }
                }
            });
        });
        return config;
    }
    
}
