package com.apprecovery.sql;

import com.apprecovery.databasecheck.CheckResult;
import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 7:08 PM
 */

@ContractXmlType(elementName = "rpDatabase")
public class Database implements Serializable
{
    private String name;
    private int id;
    private boolean isOnline;
    private boolean isDeleted;
    private boolean isAccessible;
    private DatabaseFileGroupCollection fileGroups;
    private DatabaseLogFileCollection logFiles;
    private CheckResult checkResults;

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

    @ContractXmlMethod(elementName = "id")
    public int getId()
    {
        return id;
    }

    @ContractXmlMethod(elementName = "id", isSetter = true)
    public void setId(int id)
    {
        this.id = id;
    }

    @ContractXmlMethod(elementName = "isOnline")
    public boolean isOnline()
    {
        return isOnline;
    }

    @ContractXmlMethod(elementName = "isOnline", isSetter = true)
    public void setOnline(boolean online)
    {
        isOnline = online;
    }

    @ContractXmlMethod(elementName = "isDeleted")
    public boolean isDeleted()
    {
        return isDeleted;
    }

    @ContractXmlMethod(elementName = "isDeleted", isSetter = true)
    public void setDeleted(boolean deleted)
    {
        isDeleted = deleted;
    }

    @ContractXmlMethod(elementName = "isAccessible")
    public boolean isAccessible()
    {
        return isAccessible;
    }

    @ContractXmlMethod(elementName = "isAccessible", isSetter = true)
    public void setAccessible(boolean accessible)
    {
        isAccessible = accessible;
    }

    @ContractXmlMethod(elementName = "fileGroups")
    public DatabaseFileGroupCollection getFileGroups()
    {
        return fileGroups;
    }

    @ContractXmlMethod(elementName = "fileGroups", isSetter = true)
    public void setFileGroups(DatabaseFileGroupCollection fileGroups)
    {
        this.fileGroups = fileGroups;
    }

    @ContractXmlMethod(elementName = "logFiles")
    public DatabaseLogFileCollection getLogFiles()
    {
        return logFiles;
    }

    @ContractXmlMethod(elementName = "logFiles", isSetter = true)
    public void setLogFiles(DatabaseLogFileCollection logFiles)
    {
        this.logFiles = logFiles;
    }

    @ContractXmlMethod(elementName = "checkResults")
    public CheckResult getCheckResults()
    {
        return checkResults;
    }

    @ContractXmlMethod(elementName = "checkResults", isSetter = true)
    public void setCheckResults(CheckResult checkResults)
    {
        this.checkResults = checkResults;
    }
}
