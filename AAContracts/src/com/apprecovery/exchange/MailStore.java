package com.apprecovery.exchange;

import com.apprecovery.databasecheck.CheckResult;
import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sckomoroh
 * Date: 5/12/13
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */

@ContractXmlType(elementName = "rpMailStore")
public class MailStore
        implements Serializable
{
    private String exchangeDatabasePath;
    private String logFilePath;
    private String systemPath;
    private String name;
    private CheckResult checkResults;

    @ContractXmlMethod(elementName = "exchangeDatabasePath")
    public String getExchangeDatabasePath()
    {
        return exchangeDatabasePath;
    }

    @ContractXmlMethod(elementName = "exchangeDatabasePath", isSetter = true)
    public void setExchangeDatabasePath(String exchangeDatabasePath)
    {
        this.exchangeDatabasePath = exchangeDatabasePath;
    }

    @ContractXmlMethod(elementName = "logFilePath")
    public String getLogFilePath()
    {
        return logFilePath;
    }

    @ContractXmlMethod(elementName = "logFilePath", isSetter = true)
    public void setLogFilePath(String logFilePath)
    {
        this.logFilePath = logFilePath;
    }

    @ContractXmlMethod(elementName = "systemPath")
    public String getSystemPath()
    {
        return systemPath;
    }

    @ContractXmlMethod(elementName = "systemPath", isSetter = true)
    public void setSystemPath(String systemPath)
    {
        this.systemPath = systemPath;
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

    @ContractXmlMethod(elementName = "checkResults")
    public CheckResult getCheckResults()
    {
        return checkResults;
    }

    @ContractXmlMethod(elementName = "checkResults", isSetter = true)
    public void setCheckResults(CheckResult checkResults)
    {
        this.checkResults = checkResults;
    }
}
