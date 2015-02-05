package com.apprecovery.sql;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 7:09 PM
 * To change this template use File | Settings | File Templates.
 */

@ContractXmlType(elementName = "fileGroup")
public class DatabaseFileGroup implements Serializable
{
    private int id;
    private String name;
    private boolean isDefault;
    private boolean isFileStream;
    private DatabaseDataFileCollection dataFiles;

    @ContractXmlMethod(elementName = "id")
    public int getId()
    {
        return id;
    }

    @ContractXmlMethod(elementName = "id", isSetter = true)
    public void setId(int id)
    {
        this.id = id;
    }

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

    @ContractXmlMethod(elementName = "isDefault")
    public boolean isDefault()
    {
        return isDefault;
    }

    @ContractXmlMethod(elementName = "isDefault", isSetter = true)
    public void setDefault(boolean aDefault)
    {
        isDefault = aDefault;
    }

    @ContractXmlMethod(elementName = "isFileStream")
    public boolean isFileStream()
    {
        return isFileStream;
    }

    @ContractXmlMethod(elementName = "isFileStream", isSetter = true)
    public void setFileStream(boolean fileStream)
    {
        isFileStream = fileStream;
    }

    @ContractXmlMethod(elementName = "dataFiles")
    public DatabaseDataFileCollection getDataFiles()
    {
        return dataFiles;
    }

    @ContractXmlMethod(elementName = "dataFiles", isSetter = true)
    public void setDataFiles(DatabaseDataFileCollection dataFiles)
    {
        this.dataFiles = dataFiles;
    }
}
