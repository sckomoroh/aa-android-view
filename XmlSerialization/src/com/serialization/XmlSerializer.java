package com.serialization;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.Date;

/**
 * Created by sckomoroh on 13.10.2014.
 * This class used for serialization from object to XML string
 */
public class XmlSerializer
{
    public static <T> String objectToXml(T object) throws Exception
    {
        if (object == null)
        {
            return "";
        }

        Class objectClass = object.getClass();
        Annotation typeAnnotation = objectClass.getAnnotation(ContractXmlType.class);
        if (typeAnnotation == null)
        {
            return null;
        }

        ContractXmlType contractXmlType = (ContractXmlType) typeAnnotation;
        if (contractXmlType == null)
        {
            return null;
        }

        String elementName = contractXmlType.elementName();
        String result = String.format("<%s %s>", elementName, contractXmlType.namespace());

        if (contractXmlType.isCollection())
        {
            AbstractCollection array = (AbstractCollection) object;
            if (contractXmlType.isCollectionPrimitive())
            {
                for (Object item : array)
                {
                    if (item == null)
                    {
                        result += String.format(
                                "<%s i:nil=\"true\" />",
                                contractXmlType.itemName());
                    }
                    else
                    {
                        result += String.format(
                                "<%s>%s</%s>",
                                contractXmlType.itemName(),
                                item.toString(),
                                contractXmlType.itemName());
                    }
                }
            }
            else
            {
                for (Object item : array)
                {
                    String innerXml = objectToXml(item);
                    result += innerXml;
                }
            }
        }
        else
        {
            Method[] methods = objectClass.getMethods();
            for (Method method : methods)
            {
                Annotation methodAnnotation = method.getAnnotation(ContractXmlMethod.class);
                if (methodAnnotation == null)
                {
                    continue;
                }

                ContractXmlMethod contractXmlMethod = (ContractXmlMethod) methodAnnotation;
                if (contractXmlMethod.isSetter())
                {
                    continue;
                }

                Class getReturnType = method.getReturnType();
                Object getResultObject = method.invoke(object);

                if (isPrimitive(getReturnType) || isDate(getReturnType))
                {
                    if (getResultObject == null)
                    {
                        result += String.format("<%s i:nil=\"true\" />", contractXmlMethod.elementName());
                    }
                    else
                    {
                        String valResult = isDate(getReturnType) ? dateToString((Date)getResultObject) : getResultObject.toString();
                        result += String.format(
                                "<%s>%s</%s>",
                                contractXmlMethod.elementName(),
                                valResult,
                                contractXmlMethod.elementName());
                    }
                }
                else
                {
                    String innerXml = objectToXml(getResultObject);
                    result += innerXml;
                }
            }
        }

        return result += String.format("</%s>", elementName);
    }

    static boolean isPrimitive(Class typeClass)
    {
        if (typeClass.isPrimitive())
        {
            return true;
        }

        if (typeClass.isEnum())
        {
            return true;
        }

        if (typeClass == String.class)
        {
            return true;
        }

        return false;
    }

    static boolean isDate(Class typeClass)
    {
        return typeClass == Date.class;
    }

    static String dateToString(Date object)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return df.format(object);
    }
}
