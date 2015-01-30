package com.apprecovery.repository;


import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/23/13
 * Time: 8:52 PM
 */

@ContractXmlType(elementName = "reposFileSpec")
public class RepositoryFileSpecification implements Serializable
{
    private String metadataPath;
    private String dataPath;

    @ContractXmlMethod(elementName = "metadataPath")
    public String getMetadataPath()
    {
        return metadataPath;
    }

    @ContractXmlMethod(elementName = "metadataPath", isSetter = true)
    public void setMetadataPath(String metadataPath)
    {
        this.metadataPath = metadataPath;
    }

    @ContractXmlMethod(elementName = "dataPath")
    public String getDataPath()
    {
        return dataPath;
    }

    @ContractXmlMethod(elementName = "dataPath", isSetter = true)
    public void setDataPath(String dataPath)
    {
        this.dataPath = dataPath;
    }
}
