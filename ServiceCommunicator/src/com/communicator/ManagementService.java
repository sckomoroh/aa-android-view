package com.communicator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/29/13
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ManagementService
{
    String prefix();
}
