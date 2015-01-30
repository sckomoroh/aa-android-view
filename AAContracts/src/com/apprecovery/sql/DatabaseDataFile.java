package com.apprecovery.sql;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */

@ContractXmlType(elementName = "dataFile")
public class DatabaseDataFile implements Serializable
{
    private String path;
    private boolean isPrimary;

    @ContractXmlMethod(elementName = "path")
    public String getPath()
    {
        return path;
    }

    @ContractXmlMethod(elementName = "path", isSetter = true)
    public void setPath(String path)
    {
        this.path = path;
    }

    @ContractXmlMethod(elementName = "isPrimary")
    public boolean isPrimary()
    {
        return isPrimary;
    }

    @ContractXmlMethod(elementName = "isPrimary", isSetter = true)
    public void setPrimary(boolean primary)
    {
        isPrimary = primary;
    }
}
