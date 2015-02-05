package com.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: sckomoroh
 * Date: 2/17/13
 * Time: 7:35 PM
 */

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ContractXmlType
{
    String elementName();

    String itemName() default "";

    String namespace() default "";

    boolean isCollection() default false;

    boolean isCollectionPrimitive() default true;

    Class primitiveCollectionType() default int.class;
}
