package com.apprecovery.metadata;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sckomoroh
 * Date: 5/12/13
 * Time: 5:14 PM
 */

@ContractXmlType(elementName = "coreSummaryMetadata")
public class CoreSummaryMetadata implements Serializable
{
    private String hostName;
    private OperatingSystemFamily osVersion;
    private ProcessorArchitecture osArchitecture;

    @ContractXmlMethod(elementName = "hostName")
    public String getHostName()
    {
        return hostName;
    }

    @ContractXmlMethod(elementName = "hostName", isSetter = true)
    public void setHostName(String hostName)
    {
        this.hostName = hostName;
    }

    @ContractXmlMethod(elementName = "osVersion")
    public OperatingSystemFamily getOsVersion()
    {
        return osVersion;
    }

    @ContractXmlMethod(elementName = "osVersion", isSetter = true)
    public void setOsVersion(OperatingSystemFamily osVersion)
    {
        this.osVersion = osVersion;
    }

    @ContractXmlMethod(elementName = "osArchitecture")
    public ProcessorArchitecture getOsArchitecture()
    {
        return osArchitecture;
    }

    @ContractXmlMethod(elementName = "osArchitecture", isSetter = true)
    public void setOsArchitecture(ProcessorArchitecture osArchitecture)
    {
        this.osArchitecture = osArchitecture;
    }
}
