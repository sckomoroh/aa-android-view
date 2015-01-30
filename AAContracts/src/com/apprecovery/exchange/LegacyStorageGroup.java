package com.apprecovery.exchange;

import com.apprecovery.volumeimage.VolumeImageSummary;
import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 5/16/13
 * Time: 1:55 PM
 */

@ContractXmlType(elementName = "rpLegacyStorageGroup")
public class LegacyStorageGroup implements Serializable
{
    private String logFilePath;
    private String systemPath;
    private String name;
    private LegacyMailStoreCollection mailStores;

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

    @ContractXmlMethod(elementName = "mailStores")
    public LegacyMailStoreCollection getMailStores()
    {
        return mailStores;
    }

    @ContractXmlMethod(elementName = "mailStores", isSetter = true)
    public void setMailStores(LegacyMailStoreCollection mailStores)
    {
        this.mailStores = mailStores;
    }

    public int getState()
    {
        if (mailStores == null)
        {
            return VolumeImageSummary.GRAY;
        }

        return mailStores.getState();
    }
}
