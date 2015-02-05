package com.apprecovery.backgroundjobs;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: ezvigunov
 * Date: 9/25/13
 * Time: 12:19 AM
 */

@ContractXmlType(elementName = "job")
public class BackgroundJobInfo implements Serializable
{
    private String id;
    private String summary;
    private JobStatus jobStatus;
    private Date startTime;
    private Date endTime;
    private BackgroundJobInfoCollection childJobsInfo;
    private ServerError failureReason;

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
    public JobStatus getJobStatus()
    {
        return jobStatus;
    }

    @ContractXmlMethod(elementName = "status", isSetter = true)
    public void setJobStatus(JobStatus jobStatus)
    {
        this.jobStatus = jobStatus;
    }

    @ContractXmlMethod(elementName = "startTime", isSetter = false)
    public Date getStartTime()
    {
        return startTime;
    }

    @ContractXmlMethod(elementName = "startTime", isSetter = true)
    public void setStartTime(String startTime) throws Exception
    {
        startTime = startTime.replace("T",  " ");
        this.startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
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

    @ContractXmlMethod(elementName = "childJobs", isSetter = false)
    public BackgroundJobInfoCollection getChildJobsInfo()
    {
        return childJobsInfo;
    }

    @ContractXmlMethod(elementName = "childJobs", isSetter = true)
    public void setChildJobsInfo(BackgroundJobInfoCollection childJobsInfo)
    {
        this.childJobsInfo = childJobsInfo;
    }

    @ContractXmlMethod(elementName = "failureReason", isSetter = false)
    public ServerError getFailureReason()
    {
        return failureReason;
    }

    @ContractXmlMethod(elementName = "failureReason", isSetter = true)
    public void setFailureReason(ServerError failureReason)
    {
        this.failureReason = failureReason;
    }
}
