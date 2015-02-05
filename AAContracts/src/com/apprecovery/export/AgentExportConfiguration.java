package com.apprecovery.export;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

/**
 * Created by sckomoroh
 * Created on 2/5/2015.
 */
@ContractXmlType(elementName = "exportConfig")
public class AgentExportConfiguration
{
    private String mAgentDisplayName;
    private boolean mIsEnabled;
    private boolean mIsAllowed;
    private VirtualMachineLocation mLocation;

    @ContractXmlMethod(elementName = "agentDisplayName")
    public String getAgentDisplayName()
    {
        return mAgentDisplayName;
    }

    @ContractXmlMethod(elementName = "agentDisplayName", isSetter = true)
    public void setAgentDisplayName(String mAgentDisplayName)
    {
        this.mAgentDisplayName = mAgentDisplayName;
    }

    @ContractXmlMethod(elementName = "enabled")
    public boolean isEnabled()
    {
        return mIsEnabled;
    }

    @ContractXmlMethod(elementName = "enabled", isSetter = true)
    public void setEnabled(boolean mIsEnabled)
    {
        this.mIsEnabled = mIsEnabled;
    }

    @ContractXmlMethod(elementName = "allowed")
    public boolean isAllowed()
    {
        return mIsAllowed;
    }

    @ContractXmlMethod(elementName = "allowed", isSetter = true)
    public void setAllowed(boolean mIsAllowed)
    {
        this.mIsAllowed = mIsAllowed;
    }

    @ContractXmlMethod(elementName = "location")
    public VirtualMachineLocation getLocation()
    {
        return mLocation;
    }

    @ContractXmlMethod(elementName = "loaction", isSetter = true)
    public void setLocation(VirtualMachineLocation mLocation)
    {
        this.mLocation = mLocation;
    }
}
