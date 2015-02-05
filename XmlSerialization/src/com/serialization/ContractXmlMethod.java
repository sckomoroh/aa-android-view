package com.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: sckomoroh
 * Date: 2/17/13
 * Time: 2:55 PM
 */

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ContractXmlMethod
{
    String elementName();

    boolean isSetter() default false;

    boolean isFlag() default false;

    Class flagClass() default Object.class;
}
