package com.apprecovery.repository;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/23/13
 * Time: 8:50 PM
 */

@ContractXmlType(elementName = "spec")
public class RepositorySpecification  implements Serializable
{
    private String name;
    private String comment;

    @ContractXmlMethod(elementName = "name")
    public String getName()
    {
        return name;
    }

    @ContractXmlMethod(elementName = "name", isSetter = true)
    public void setName(String name)
    {
        this.name = name;
    }

    @ContractXmlMethod(elementName = "comment")
    public String getComment()
    {
        return comment;
    }

    @ContractXmlMethod(elementName = "comment", isSetter = true)
    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
