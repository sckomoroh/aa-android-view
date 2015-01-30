package com.apprecovery.agents;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/29/13
 * Time: 5:36 PM
 */

@ContractXmlType(elementName = "agentInfoSummary")
public class AgentInfoSummary implements Serializable
{
    private String agentType;
    private String displayName;
    private String id;
    boolean isCluster;
    public String parentClusterId;
    boolean recoveryPointsOnly;

    @ContractXmlMethod(elementName = "agentType")
    public String getAgentType()
    {
        return agentType;
    }

    @ContractXmlMethod(elementName = "agentType", isSetter = true)
    public void setAgentType(String agentType)
    {
        this.agentType = agentType;
    }

    @ContractXmlMethod(elementName = "displayName")
    public String getDisplayName()
    {
        return displayName;
    }

    @ContractXmlMethod(elementName = "displayName", isSetter = true)
    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    @ContractXmlMethod(elementName = "id")
    public String getId()
    {
        return id;
    }

    @ContractXmlMethod(elementName = "id", isSetter = true)
    public void setId(String id)
    {
        this.id = id;
    }

    @ContractXmlMethod(elementName = "isCluster")
    public boolean isCluster()
    {
        return isCluster;
    }

    @ContractXmlMethod(elementName = "isCluster", isSetter = true)
    public void setCluster(boolean cluster)
    {
        isCluster = cluster;
    }

    @ContractXmlMethod(elementName = "parentClusterId")
    public String getParentClusterId()
    {
        return parentClusterId;
    }

    @ContractXmlMethod(elementName = "parentClusterId", isSetter = true)
    public void setParentClusterId(String parentClusterId)
    {
        this.parentClusterId = parentClusterId;
    }

    @ContractXmlMethod(elementName = "recoveryPointsOnly")
    public boolean isRecoveryPointsOnly()
    {
        return recoveryPointsOnly;
    }

    @ContractXmlMethod(elementName = "recoveryPointsOnly", isSetter = true)
    public void setRecoveryPointsOnly(boolean recoveryPointsOnly)
    {
        this.recoveryPointsOnly = recoveryPointsOnly;
    }
}
