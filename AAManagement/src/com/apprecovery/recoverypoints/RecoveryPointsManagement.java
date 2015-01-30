package com.apprecovery.recoverypoints;

import com.apprecovery.BaseManagement;
import com.communicator.ManagementMethod;
import com.communicator.ManagementService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/29/13
 * Time: 8:09 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagementService(prefix = "recoveryPoints")
public class RecoveryPointsManagement extends BaseManagement
{
    public RecoveryPointsManagement()
    {
        createInvoker();
    }

    @ManagementMethod(urlTemplate = "agents/{agentId}/all")
    public RecoveryPointSummaryInfoCollection getAllRecoveryPoints(String agentId) throws Exception
    {
        Map<String, String> args = new HashMap<String, String>();
        args.put("{agentId}", agentId);

        return invoker.invokeManagementMethodGet(args);
    }

    @ManagementMethod(urlTemplate = "agents/{agentId}/paged?max={max}&page={page}")
    public RecoveryPointsResponse getRecoveryPointsByPage(String agentId, Integer max, Integer page) throws Exception
    {
        Map<String, String> args = new HashMap<String, String>();
        args.put("{agentId}", agentId);
        args.put("{max}", max.toString());
        args.put("{page}", page.toString());

        return invoker.invokeManagementMethodGet(args);
    }
}
