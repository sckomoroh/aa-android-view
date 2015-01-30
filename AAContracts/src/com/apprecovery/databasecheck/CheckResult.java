package com.apprecovery.databasecheck;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 8:23 PM
 */

@ContractXmlType(elementName = "checkResults")
public class CheckResult implements Serializable
{
    private int flags;

    @ContractXmlMethod(elementName = "flags", isFlag = true, flagClass = CheckFlags.class)
    public int getCheckResults()
    {
        return flags;
    }

    @ContractXmlMethod(elementName = "flags", isSetter = true, isFlag = true, flagClass = CheckFlags.class)
    public void setCheckResults(int checkResults)
    {
        this.flags = checkResults;
    }
}
