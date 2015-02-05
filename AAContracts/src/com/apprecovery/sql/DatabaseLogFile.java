package com.apprecovery.sql;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 7:13 PM
 */

@ContractXmlType(elementName = "logFile")
public class DatabaseLogFile implements Serializable
{
    private String path;

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
}
