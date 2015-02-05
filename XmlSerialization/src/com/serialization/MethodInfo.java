package com.serialization;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/24/13
 * Time: 2:15 AM
 */

public class MethodInfo implements Map.Entry<ContractXmlMethod, Method>
{
    private ContractXmlMethod xmlMethod;
    private Method method;

    public MethodInfo(ContractXmlMethod xm, Method m)
    {
        xmlMethod = xm;
        method = m;
    }

    @Override
    public boolean equals(Object o)
    {
        return false;
    }

    @Override
    public ContractXmlMethod getKey()
    {
        return xmlMethod;
    }

    @Override
    public Method getValue()
    {
        return method;
    }

    @Override
    public int hashCode()
    {
        return 0;
    }

    @Override
    public Method setValue(Method method)
    {
        return method;
    }
}