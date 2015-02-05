package com.apprecovery.recoverypoints;

import com.apprecovery.volumeimage.VolumeImageSummaryCollection;
import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/29/13
 * Time: 8:42 PM
 */

@ContractXmlType(elementName = "recoveryPointSummary")
public class RecoveryPointSummaryInfo implements Serializable
{
    private String agentHostName;
    private String agentId;
    private String id;
    private long size;
    private String status;
    private VolumeImageSummaryCollection volumeImages;
    private Date timeStamp;

    @ContractXmlMethod(elementName = "timeStamp")
    public Date getTimeStamp()
    {
        return timeStamp;
    }

    @ContractXmlMethod(elementName = "timeStamp", isSetter = true)
    public void setTimeStamp(String timeStamp) throws Exception
    {
        timeStamp = timeStamp.replace("T",  " ");
        this.timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStamp);
    }

    @ContractXmlMethod(elementName = "agentHostName")
    public String getAgentHostName()
    {
        return agentHostName;
    }

    @ContractXmlMethod(elementName = "agentHostName", isSetter = true)
    public void setAgentHostName(String agentHostName)
    {
        this.agentHostName = agentHostName;
    }

    @ContractXmlMethod(elementName = "agentId")
    public String getAgentId()
    {
        return agentId;
    }

    @ContractXmlMethod(elementName = "agentId", isSetter = true)
    public void setAgentId(String agentId)
    {
        this.agentId = agentId;
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

    @ContractXmlMethod(elementName = "size")
    public long getSize()
    {
        return size;
    }

    @ContractXmlMethod(elementName = "size", isSetter = true)
    public void setSize(long size)
    {
        this.size = size;
    }

    @ContractXmlMethod(elementName = "volumeImages")
    public VolumeImageSummaryCollection getVolumeImages()
    {
        return volumeImages;
    }

    @ContractXmlMethod(elementName = "volumeImages", isSetter = true)
    public void setVolumeImages(VolumeImageSummaryCollection volumeImages)
    {
        this.volumeImages = volumeImages;
    }

    @ContractXmlMethod(elementName = "status")
    public String getStatus()
    {
        return status;
    }

    @ContractXmlMethod(elementName = "status", isSetter = true)
    public void setStatus(String status)
    {
        this.status = status;
    }
}
