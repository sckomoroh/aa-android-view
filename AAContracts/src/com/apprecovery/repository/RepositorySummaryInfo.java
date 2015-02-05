package com.apprecovery.repository;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/23/13
 * Time: 8:46 PM
 */

@ContractXmlType(elementName = "repositorySummary")
public class RepositorySummaryInfo
{
    private String id;
    private long size;
    private long free;
    private boolean network;
    private RepositoryStatus status;
    private RepositoryMaintenanceState repositoryMaintenanceState;
    private RepositoryFileInfoCollection files;
    private RepositorySpecification spec;

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

    @ContractXmlMethod(elementName = "free")
    public long getFree()
    {
        return free;
    }

    @ContractXmlMethod(elementName = "free", isSetter = true)
    public void setFree(long free)
    {
        this.free = free;
    }

    @ContractXmlMethod(elementName = "network")
    public boolean isNetwork()
    {
        return network;
    }

    @ContractXmlMethod(elementName = "network", isSetter = true)
    public void setNetwork(boolean network)
    {
        this.network = network;
    }

    @ContractXmlMethod(elementName = "status")
    public RepositoryStatus getStatus()
    {
        return status;
    }

    @ContractXmlMethod(elementName = "status", isSetter = true)
    public void setStatus(RepositoryStatus status)
    {
        this.status = status;
    }

    @ContractXmlMethod(elementName = "repositoryMaintenanceState")
    public RepositoryMaintenanceState getRepositoryMaintenanceState()
    {
        return repositoryMaintenanceState;
    }

    @ContractXmlMethod(elementName = "repositoryMaintenanceState", isSetter = true)
    public void setRepositoryMaintenanceState(RepositoryMaintenanceState repositoryMaintenanceState)
    {
        this.repositoryMaintenanceState = repositoryMaintenanceState;
    }

    @ContractXmlMethod(elementName = "files")
    public RepositoryFileInfoCollection getFiles()
    {
        return files;
    }

    @ContractXmlMethod(elementName = "files", isSetter = true)
    public void setFiles(RepositoryFileInfoCollection files)
    {
        this.files = files;
    }

    @ContractXmlMethod(elementName = "spec")
    public RepositorySpecification getSpec()
    {
        return spec;
    }

    @ContractXmlMethod(elementName = "spec", isSetter = true)
    public void setSpec(RepositorySpecification spec)
    {
        this.spec = spec;
    }
}
