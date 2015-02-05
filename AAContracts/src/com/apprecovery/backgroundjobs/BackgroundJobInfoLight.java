package com.apprecovery.backgroundjobs;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sckomoroh on 13.10.2014.
 */
@ContractXmlType(elementName = "jobLight")
public class BackgroundJobInfoLight
{
    private String id;
    private String summary;
    private JobStatus status;
    private Date creationTime;
    private Date endTime;

    @ContractXmlMethod(elementName = "id", isSetter = false)
    public String getId()
    {
        return id;
    }

    @ContractXmlMethod(elementName = "id", isSetter = true)
    public void setId(String id)
    {
        this.id = id;
    }

    @ContractXmlMethod(elementName = "summary", isSetter = false)
    public String getSummary()
    {
        return summary;
    }

    @ContractXmlMethod(elementName = "summary", isSetter = true)
    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    @ContractXmlMethod(elementName = "status", isSetter = false)
    public JobStatus getStatus()
    {
        return status;
    }

    @ContractXmlMethod(elementName = "status", isSetter = true)
    public void setStatus(JobStatus status)
    {
        this.status = status;
    }

    @ContractXmlMethod(elementName = "creationTime", isSetter = false)
    public Date getCreationTime()
    {
        return creationTime;
    }

    @ContractXmlMethod(elementName = "creationTime", isSetter = true)
    public void setCreationTime(String creationTime) throws Exception
    {
        creationTime = creationTime.replace("T",  " ");
        this.creationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(creationTime);
    }

    @ContractXmlMethod(elementName = "endTime", isSetter = false)
    public Date getEndTime()
    {
        return endTime;
    }

    @ContractXmlMethod(elementName = "endTime", isSetter = true)
    public void setEndTime(String endTime) throws Exception
    {
        endTime = endTime.replace("T",  " ");
        this.endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
    }
}
