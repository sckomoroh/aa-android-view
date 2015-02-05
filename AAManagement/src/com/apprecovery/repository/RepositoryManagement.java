package com.apprecovery.repository;

import com.apprecovery.BaseManagement;
import com.communicator.ManagementMethod;
import com.communicator.ManagementService;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/23/13
 * Time: 9:06 PM
 */

@ManagementService(prefix = "reposManagement")
public class RepositoryManagement extends BaseManagement
{
    public RepositoryManagement()
    {
        createInvoker();
    }

    @ManagementMethod(urlTemplate = "repositorySummaries")
    public RepositorySummaryInfoCollection GetRepositorySummaries() throws Exception
    {
        return invoker.invokeManagementMethodGet(null);
    }
}
