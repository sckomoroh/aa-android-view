package com.apprecovery.events;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/24/13
 * Time: 11:52 PM
 */

@ContractXmlType(elementName = "eventSummary")
public class EventSummary implements Serializable
{
    private EventLevel level;
    private String message;
    private boolean alert;
    private String timeStamp;

    @ContractXmlMethod(elementName = "level", isSetter = false)
    public EventLevel getLevel()
    {
        return level;
    }

    @ContractXmlMethod(elementName = "level", isSetter = true)
    public void setLevel(EventLevel level)
    {
        this.level = level;
    }

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

    @ContractXmlMethod(elementName = "alert", isSetter = false)
    public boolean isAlert()
    {
        return alert;
    }

    @ContractXmlMethod(elementName = "alert", isSetter = true)
    public void setAlert(boolean alert)
    {
        this.alert = alert;
    }

    @ContractXmlMethod(elementName = "utcTimestamp", isSetter = false)
    public String getTimeStamp()
    {
        return timeStamp;
    }

    @ContractXmlMethod(elementName = "utcTimestamp", isSetter = true)
    public void setTimeStamp(String timeStamp)
    {
        this.timeStamp = timeStamp;
    }
}
