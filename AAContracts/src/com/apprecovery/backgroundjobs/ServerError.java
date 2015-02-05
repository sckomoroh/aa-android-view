package com.apprecovery.backgroundjobs;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created by sckomoroh on 13.10.2014.
 */
@ContractXmlType(elementName = "failureReason")
public class ServerError implements Serializable
{
    private String message;
    private String details;

    @ContractXmlMethod(elementName = "message", isSetter = false)
    public String getMessage()
    {
        return message;
    }

    @ContractXmlMethod(elementName = "message", isSetter = true)
    public void setMessage(String message)
    {
        this.message = message;
    }

    @ContractXmlMethod(elementName = "detail", isSetter = false)
    public String getDetails()
    {
        return details;
    }

    @ContractXmlMethod(elementName = "detail", isSetter = true)
    public void setDetails(String details)
    {
        this.details = details;
    }
}
