package com.apprecovery.sql;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 7:16 PM
 * To change this template use File | Settings | File Templates.
 */

@ContractXmlType(elementName = "instance")
public class SqlInstance implements Serializable
{
    private String name;
    private String version;
    private String versionNumber;
    private DatabaseCollection databases;

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

    @ContractXmlMethod(elementName = "version")
    public String getVersion()
    {
        return version;
    }

    @ContractXmlMethod(elementName = "version", isSetter = true)
    public void setVersion(String version)
    {
        this.version = version;
    }

    @ContractXmlMethod(elementName = "versionNumber")
    public String getVersionNumber()
    {
        return versionNumber;
    }

    @ContractXmlMethod(elementName = "versionNumber", isSetter = true)
    public void setVersionNumber(String versionNumber)
    {
        this.versionNumber = versionNumber;
    }

    @ContractXmlMethod(elementName = "databases")
    public DatabaseCollection getDatabases()
    {
        return databases;
    }

    @ContractXmlMethod(elementName = "databases", isSetter = true)
    public void setDatabases(DatabaseCollection databases)
    {
        this.databases = databases;
    }
}
