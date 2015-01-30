package com.apprecovery.recoverypoints;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 10:16 PM
 */

@ContractXmlType(elementName = "getRecoveryPointsResponse")
public class RecoveryPointsResponse implements Serializable
{
    private RecoveryPointSummaryInfoCollection2 recoveryPoints;
    private int total;

    @ContractXmlMethod(elementName = "recoveryPoints")
    public RecoveryPointSummaryInfoCollection2 getRecoveryPoints()
    {
        return recoveryPoints;
    }

    @ContractXmlMethod(elementName = "recoveryPoints", isSetter = true)
    public void setRecoveryPoints(RecoveryPointSummaryInfoCollection2 recoveryPoints)
    {
        this.recoveryPoints = recoveryPoints;
    }

    @ContractXmlMethod(elementName = "total")
    public int getTotal()
    {
        return total;
    }

    @ContractXmlMethod(elementName = "total", isSetter = true)
    public void setTotal(int total)
    {
        this.total = total;
    }
}
