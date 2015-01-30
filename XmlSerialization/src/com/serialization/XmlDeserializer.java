package com.serialization;

import android.util.Log;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringBufferInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/23/13
 * Time: 10:40 PM
 */

public class XmlDeserializer
{
    public static void addClass(String fullClassName) throws Exception
    {
        try
        {
            Class cls = Class.forName(fullClassName);

            for (ClassInfo classInfo : ClassesStorage.classes)
            {
                if (classInfo.getXmlClass() == cls)
                {
                    throw new IllegalArgumentException("The class already registered");
                }
            }

            ContractXmlType typeAnnotation = (ContractXmlType)cls.getAnnotation(ContractXmlType.class);

            if (typeAnnotation != null)
            {
                ClassesStorage.classes.add(new ClassInfo(typeAnnotation, cls));
            }
            else
            {
                throw new NullPointerException("The necessary annotation could not be found");
            }
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static <T> T xmlToObject(String xml, Class returnType) throws Exception
    {
        Element root = getDocument(xml).getDocumentElement();

        String className = root.getTagName();
        ClassInfo ci = findClassInfoByTagName(className);
        T obj = null;

        if (ci == null)
        {
            if (returnType.isPrimitive())
            {
                Class primitiveClass = getPrimitiveClass(returnType);

                obj = createPrimitiveObject(root, primitiveClass, returnType);

                return obj;
            }

            throw new IllegalArgumentException("Class not found");
        }

        obj = createObject(ci.getXmlClass());

        if (ci.getTypeAnnotation().isCollection())
        {
            createCollection(root, ci, (Collection)obj);
        }
        else
        {
            buildObject(root, ci, obj);
        }

        return obj;
    }

    /*
     *  Create methods
     */
    private static <T> T createObject(Class objectClass) throws Exception
    {
        return (T)objectClass.getConstructor(null).newInstance(null);
    }

    private static <T> T createPrimitiveObject(Element element, Class objectClass, Class wrappedClass) throws Exception
    {
        Node textNode = element.getFirstChild();
        Object val = null;

        if (textNode.getNodeType() == Node.TEXT_NODE)
        {
            Text text = (Text) textNode;
            String value = text.getWholeText();

            if (objectClass == Long.class)
            {
                val = Long.parseLong(value);
            }
            else if (objectClass == Integer.class)
            {
                val = Integer.parseInt(value);
            }
        }

        return (T)objectClass.getConstructor(wrappedClass).newInstance(val);
    }

    private static int createFlagValue(String values, Class flagClass) throws Exception
    {
        String[] flagValues = values.split(" ");
        Field[] flagFields = flagClass.getFields();
        Object flagInst = createObject(flagClass);

        int result = 0;

        for (String val : flagValues)
        {
            for (Field field : flagFields)
            {
                if (field.getName().equals(val))
                {
                    result |= field.getInt(flagInst);
                    break;
                }
            }
        }

        return result;
    }

    private static void createPrimitive(Element element, ClassInfo classInfo, Object obj) throws Exception
    {
        String elementName = element.getTagName();
        Node textNode = element.getFirstChild();

        if (textNode.getNodeType() == Node.TEXT_NODE)
        {
            Text text = (Text)textNode;
            String value = text.getWholeText();

            setValue(elementName, value, classInfo, obj);
        }
    }

    private static Object getPrimitiveValue(Class cls, Element element)
    {
        Node textNode = element.getFirstChild();

        if (textNode.getNodeType() == Node.TEXT_NODE)
        {
            Text text = (Text)textNode;
            String value = text.getWholeText();

            if (cls == boolean.class)
            {
                return Boolean.parseBoolean(value);
            }
            else if (cls == int.class)
            {
                return Integer.parseInt(value);
            }
            else if (cls == long.class)
            {
                return Long.parseLong(value);
            }
            else if (cls == double.class)
            {
                return Double.parseDouble(value);
            }
            else if (cls.isEnum())
            {
                return Enum.valueOf(cls, value);
            }
            else
            {
                return value;
            }
        }

        return null;
    }

    private static <T extends Collection> void createCollection(Element element, ClassInfo classInfo, T obj) throws Exception
    {
        String itemName = classInfo.getTypeAnnotation().itemName();
        NodeList nodeList = element.getElementsByTagName(itemName);
        for (int i=0; i<nodeList.getLength(); i++)
        {
            Object subObj = null;
            Element itemElement = (Element)nodeList.item(i);

            if (classInfo.getTypeAnnotation().isCollectionPrimitive())
            {
                subObj = getPrimitiveValue(classInfo.getTypeAnnotation().primitiveCollectionType(), itemElement);
            }
            else
            {
                String name = itemElement.getTagName();
                ClassInfo ci = findClassInfoByTagName(name);

                if (ci != null)
                {
                    subObj = createObject(ci.getXmlClass());

                    buildObject(itemElement, ci, subObj);
                }
            }

            obj.add(subObj);
        }
    }

    private static void buildObject(Element rootElement, ClassInfo classInfo, Object obj) throws Exception
    {
        NodeList nodeList = rootElement.getChildNodes();

        for(int i=0; i<nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element)node;

                if (element.getFirstChild() != null)
                {
                    if (element.getFirstChild().getNodeType() != Node.TEXT_NODE)
                    {
                        String name = element.getTagName();

                        MethodInfo setterInfo = classInfo.findSetterByTagName(name);

                        if (setterInfo == null)
                        {
                            continue;
                        }

                        Class type = setterInfo.getValue().getParameterTypes()[0];
                        ClassInfo ci = findClassInfoByClass(type);
                        if (ci != null)
                        {
                            Object subObj = createObject(ci.getXmlClass());

                            if (ci.getTypeAnnotation().isCollection())
                            {
                                createCollection(element, ci, (Collection)subObj);
                            }
                            else
                            {
                                buildObject(element, ci, subObj);
                            }

                            String methodName = element.getTagName();
                            setValue(methodName, subObj, classInfo, obj);
                        }
                    }
                    else
                    {
                        createPrimitive(element, classInfo, obj);
                    }
                }
            }
        }
    }

    private static void setValue(String name, Object value, ClassInfo classInfo, Object instance) throws Exception
    {
        MethodInfo methodInfo =  classInfo.findSetterByTagName(name);
        if (methodInfo != null && methodInfo.getKey().isSetter())
        {
            methodInfo.getValue().invoke(instance, value);
        }
    }

    private static void setValue(String name, String value, ClassInfo classInfo, Object instance) throws Exception
    {
        MethodInfo methodInfo = classInfo.findSetterByTagName(name);

        if (methodInfo != null && methodInfo.getKey().isSetter())
        {
            if (methodInfo.getKey().isFlag())
            {
                int flagValue = createFlagValue(value, methodInfo.getKey().flagClass());

                methodInfo.getValue().invoke(instance, flagValue);
            }
            else
            {
                if (methodInfo.getValue().getParameterTypes()[0] == boolean.class)
                {
                    methodInfo.getValue().invoke(instance, Boolean.parseBoolean(value));
                }
                else if (methodInfo.getValue().getParameterTypes()[0] == int.class)
                {
                    methodInfo.getValue().invoke(instance, Integer.parseInt(value));
                }
                else if (methodInfo.getValue().getParameterTypes()[0] == long.class)
                {
                    methodInfo.getValue().invoke(instance, Long.parseLong(value));
                }
                else if (methodInfo.getValue().getParameterTypes()[0] == double.class)
                {
                    methodInfo.getValue().invoke(instance, Double.parseDouble(value));
                }
                else if (methodInfo.getValue().getParameterTypes()[0].isEnum())
                {
                    Class enumClass = methodInfo.getValue().getParameterTypes()[0];
                    methodInfo.getValue().invoke(instance, Enum.valueOf(enumClass, value));
                }
                else
                {
                    methodInfo.getValue().invoke(instance, value);
                }
            }
        }
    }

    /*
     *  Utils methods
     */
    private static Document getDocument(String xmlContent) throws Exception
    {
        StringBufferInputStream stream = new StringBufferInputStream(xmlContent);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder().parse(stream);
    }

    private static ClassInfo findClassInfoByTagName(String tagName)
    {
        for(ClassInfo ci : ClassesStorage.classes)
        {
            String ciTagName = ci.getTagName();
            if (ciTagName.equals(tagName))
            {
                return ci;
            }
        }

        return null;
    }

    private static ClassInfo findClassInfoByClass(Class cls)
    {
        for(ClassInfo ci : ClassesStorage.classes)
        {
            Class tagClass = ci.getXmlClass();
            if (tagClass.equals(cls))
            {
                return ci;
            }
        }

        return null;
    }

    private static Class getPrimitiveClass(Class returnType)
    {
        if (returnType == long.class)
        {
            return Long.class;
        }

        if (returnType == int.class)
        {
            return Integer.class;
        }

        return null;
    }
}
