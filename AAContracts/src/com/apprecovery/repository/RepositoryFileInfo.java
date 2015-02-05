package com.apprecovery.repository;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/23/13
 * Time: 8:55 PM
 */

@ContractXmlType(elementName = "reposFileInfo")
public class RepositoryFileInfo implements Serializable
{
    private RepositoryFileSpecification spec;

    @ContractXmlMethod(elementName = "spec")
    public RepositoryFileSpecification getSpecification()
    {
        return spec;
    }

    @ContractXmlMethod(elementName = "spec", isSetter = true)
    public void setSpecification(RepositoryFileSpecification spec)
    {
        this.spec = spec;
    }
}
