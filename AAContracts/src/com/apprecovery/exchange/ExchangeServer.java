package com.apprecovery.exchange;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sckomoroh
 * Date: 5/12/13
 * Time: 1:11 PM
 */

@ContractXmlType(elementName = "exchange")
public class ExchangeServer implements Serializable
{
    private ExchangeServerVersion version;
    private MailStoreCollection mailStores;
    private  LegacyStorageGroupCollection legacyStorageGroups;

    @ContractXmlMethod(elementName = "version")
    public ExchangeServerVersion getVersion()
    {
        return version;
    }

    @ContractXmlMethod(elementName = "version", isSetter = true)
    public void setVersion(ExchangeServerVersion version)
    {
        this.version = version;
    }

    @ContractXmlMethod(elementName = "mailStores")
    public MailStoreCollection getMailStores()
    {
        return mailStores;
    }

    @ContractXmlMethod(elementName = "mailStores", isSetter = true)
    public void setMailStores(MailStoreCollection mailStores)
    {
        this.mailStores = mailStores;
    }

    @ContractXmlMethod(elementName = "legacyStorageGroups")
    public LegacyStorageGroupCollection getLegacyStorageGroups()
    {
        return legacyStorageGroups;
    }

    @ContractXmlMethod(elementName = "legacyStorageGroups", isSetter = true)
    public void setLegacyStorageGroups(LegacyStorageGroupCollection legacyStorageGroups)
    {
        this.legacyStorageGroups = legacyStorageGroups;
    }
}
