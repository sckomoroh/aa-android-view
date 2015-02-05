package com.apprecovery.backgroundjobs;

import com.apprecovery.BaseManagement;
import com.communicator.ManagementMethod;
import com.communicator.ManagementService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/25/13
 * Time: 12:26 AM
 */

@ManagementService(prefix = "jobmgr")
public class BackgroundJobManagement extends BaseManagement
{
    public BackgroundJobManagement()
    {
        createInvoker();
    }

    @ManagementMethod(urlTemplate = "jobs/{jobId}")
    public BackgroundJobInfo getJob(String jobId) throws Exception
    {
        Map<String, String> args = new HashMap<>();
        args.put("{jobId}", jobId.toString());

        return invoker.invokeManagementMethodGet(args);
    }

    @ManagementMethod(urlTemplate = "jobs/paged/{jobsPerPage}/{page}", httpMethod = "PUT")
    public BackgroundJobInfoLightCollection getJobsByPage(Integer page, Integer jobsPerPage, BackgroundJobSearchParameters params) throws Exception
    {
        Map<String, String> args = new HashMap<>();
        args.put("{jobsPerPage}", jobsPerPage.toString());
        args.put("{page}", page.toString());

        return invoker.invokeManagementMethodPut(args, params);
    }

    @ManagementMethod(urlTemplate = "jobs/count", httpMethod = "PUT")
    public int getJobsCount(BackgroundJobSearchParameters params) throws Exception
    {
        Map<String, String> args = new HashMap<>();

        return invoker.invokeManagementMethodPut(args, params);
    }
}
