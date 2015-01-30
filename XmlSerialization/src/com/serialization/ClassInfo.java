package com.serialization;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/24/13
 * Time: 2:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClassInfo
{
    private Class _class;
    private ContractXmlType _typeAnnotation;
    private List<MethodInfo> _methods;

    public ClassInfo(ContractXmlType xmlType, Class cls)
    {
        _class = cls;
        _typeAnnotation = xmlType;
        _methods = new ArrayList<MethodInfo>();

        retrieveMethodsInfo();
    }

    public String getTagName()
    {
        return _typeAnnotation.elementName();
    }

    public Class getXmlClass()
    {
        return _class;
    }

    public ContractXmlType getTypeAnnotation()
    {
        return _typeAnnotation;
    }

    public List<MethodInfo> getMethodsInfo()
    {
        return _methods;
    }

    public MethodInfo findSetterByTagName(String tagName)
    {
        for(MethodInfo methodInfo : _methods)
        {
            if (methodInfo.getKey().elementName().equals(tagName) && methodInfo.getKey().isSetter())
            {
                return methodInfo;
            }
        }

        return null;
    }

    private void retrieveMethodsInfo()
    {
        Method[] methods = _class.getMethods();

        for(Method method : methods)
        {
            ContractXmlMethod methodAnno = method.getAnnotation(ContractXmlMethod.class);
            if (methodAnno != null)
            {
                _methods.add(new MethodInfo(methodAnno, method));
            }
        }
    }
}

