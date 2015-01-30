package com.apprecovery.backgroundjobs;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.util.Date;

/**
 * Created by sckomoroh on 12.10.2014.
 */
@ContractXmlType(elementName = "backgroundJobSearchParameters", namespace = "xmlns=\"http://schemas.datacontract.org/2004/07/Replay.Core.Contracts.BackgroundJobs\"")
public class BackgroundJobSearchParameters
{
    private AgentIdsCollection agentIds;
    private BackgroundJobSearchMethod summarySearchMethod;
    private String summarySearchPattern;
    private String summarySearchOptions;
    private JobStatusFlags jobStatusFlags;
    private Date minStartTime;
    private Date maxStartTime;
    private BackgroundJobKindFlags jobKindFlags;
    private BackgroundJobTypeCollection jobsTypeCollection;

    public BackgroundJobSearchParameters()
    {
        jobsTypeCollection = new BackgroundJobTypeCollection();
    }

    @ContractXmlMethod(elementName = "agentIds", isSetter = false)
    public AgentIdsCollection getAgentIds()
    {
        return agentIds;
    }

    @ContractXmlMethod(elementName = "agentIds", isSetter = true)
    public void setAgentIds(AgentIdsCollection agentIds)
    {
        this.agentIds = agentIds;
    }

    @ContractXmlMethod(elementName = "summarySearchMethod", isSetter = false)
    public BackgroundJobSearchMethod getSummarySearchMethod()
    {
        return summarySearchMethod;
    }

    @ContractXmlMethod(elementName = "summarySearchMethod", isSetter = true)
    public void setSummarySearchMethod(BackgroundJobSearchMethod summarySearchMethod)
    {
        this.summarySearchMethod = summarySearchMethod;
    }

    @ContractXmlMethod(elementName = "summarySearchPattern", isSetter = false)
    public String getSummarySearchPattern()
    {
        return summarySearchPattern;
    }

    @ContractXmlMethod(elementName = "summarySearchPattern", isSetter = true)
    public void setSummarySearchPattern(String summarySearchPattern)
    {
        this.summarySearchPattern = summarySearchPattern;
    }

    @ContractXmlMethod(elementName = "summarySearchOptions", isSetter = false)
    public String getSummarySearchOptions()
    {
        return summarySearchOptions;
    }

    @ContractXmlMethod(elementName = "summarySearchOptions", isSetter = true)
    public void setSummarySearchOptions(String summarySearchOptions)
    {
        this.summarySearchOptions = summarySearchOptions;
    }

    @ContractXmlMethod(elementName = "jobStatusFlags", isSetter = false)
    public JobStatusFlags getJobStatusFlags()
    {
        return jobStatusFlags;
    }

    @ContractXmlMethod(elementName = "jobStatusFlags", isSetter = true)
    public void setJobStatusFlags(JobStatusFlags jobStatusFlags)
    {
        this.jobStatusFlags = jobStatusFlags;
    }

    @ContractXmlMethod(elementName = "minStartTime", isSetter = false)
    public Date getMinStartTime()
    {
        return minStartTime;
    }

    @ContractXmlMethod(elementName = "minStartTime", isSetter = true)
    public void setMinStartTime(Date minStartTime)
    {
        this.minStartTime = minStartTime;
    }

    @ContractXmlMethod(elementName = "maxStartTime", isSetter = false)
    public Date getMaxStartTime()
    {
        return maxStartTime;
    }

    @ContractXmlMethod(elementName = "maxStartTime", isSetter = true)
    public void setMaxStartTime(Date maxStartTime)
    {
        this.maxStartTime = maxStartTime;
    }

    @ContractXmlMethod(elementName = "jobKindFlags", isSetter = false)
    public BackgroundJobKindFlags getJobKindFlags()
    {
        return jobKindFlags;
    }

    @ContractXmlMethod(elementName = "jobKindFlags", isSetter = true)
    public void setJobKindFlags(BackgroundJobKindFlags jobKindFlags)
    {
        this.jobKindFlags = jobKindFlags;
    }

    @ContractXmlMethod(elementName = "backgroundJobTypes", isSetter = true)
    public void setBackgroundJobTypes(BackgroundJobTypeCollection collection)
    {
        jobsTypeCollection = collection;
    }

    @ContractXmlMethod(elementName = "backgroundJobTypes", isSetter = false)
    public BackgroundJobTypeCollection getBackgroundJobTypes()
    {
        return jobsTypeCollection;
    }
}
