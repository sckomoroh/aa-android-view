package com.apprecovery.agents;

import com.apprecovery.BaseManagement;
import com.communicator.ManagementMethod;
import com.communicator.ManagementService;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/29/13
 * Time: 6:42 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagementService(prefix = "agents")
public class AgentManagement extends BaseManagement
{
    public AgentManagement()
    {
        createInvoker();
    }

    @ManagementMethod(urlTemplate = "infosummaries")
    public AgentInfoSummaryCollection getAgentInfoSummaries() throws Exception
    {
        return invoker.invokeManagementMethodGet(null);
    }
}
