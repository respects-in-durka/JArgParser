package com.nikitka.jargparser.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Argument {
    /**
     * Aliases for an argument, default variable name
     * @throws {@link IllegalArgumentException} if aliases are {} and exclude is true
     * @return String[]
     */
    String[] aliases() default {};
    /** 
     * Is argument required, default true
     * @return {@link Boolean}
     */
    boolean required() default true;

    /**
     * Exclude variable name from aliases
     * @throws {@link IllegalArgumentException} if aliases are empty and exclude is true
     * @return {@link Boolean}
     */
    boolean exclude() default false;
}
