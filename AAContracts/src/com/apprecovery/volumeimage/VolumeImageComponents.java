package com.apprecovery.volumeimage;

import com.apprecovery.exchange.ExchangeServer;
import com.apprecovery.sql.SqlServer;
import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 7:20 PM
 */

@ContractXmlType(elementName = "volumeImageComponents")
public class VolumeImageComponents implements Serializable
{
    private String volumeGroupDisplayName;
    private SqlServer sqlServer;
    private ExchangeServer exchange;

    @ContractXmlMethod(elementName = "volumeGroupDisplayName")
    public String getVolumeGroupDisplayName()
    {
        return volumeGroupDisplayName;
    }

    @ContractXmlMethod(elementName = "volumeGroupDisplayName", isSetter = true)
    public void setVolumeGroupDisplayName(String volumeGroupDisplayName)
    {
        this.volumeGroupDisplayName = volumeGroupDisplayName;
    }

    @ContractXmlMethod(elementName = "sqlServer")
    public SqlServer getSqlServer()
    {
        return sqlServer;
    }

    @ContractXmlMethod(elementName = "sqlServer", isSetter = true)
    public void setSqlServer(SqlServer sqlServer)
    {
        this.sqlServer = sqlServer;
    }

    @ContractXmlMethod(elementName = "exchange")
    public ExchangeServer getExchangeServer()
    {
        return exchange;
    }

    @ContractXmlMethod(elementName = "exchange", isSetter = true)
    public void setExchangeServer(ExchangeServer exchangeServer)
    {
        this.exchange = exchangeServer;
    }
}
