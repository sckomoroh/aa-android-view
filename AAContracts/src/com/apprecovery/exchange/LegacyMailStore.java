package com.apprecovery.exchange;

import com.apprecovery.databasecheck.CheckResult;
import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 5/16/13
 * Time: 1:54 PM
 */

@ContractXmlType(elementName = "rpLegacyMailStore")
public class LegacyMailStore implements Serializable
{
    private String name;
    private CheckResult checkResults;
    private String exchangeDatabasePath;

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
}
