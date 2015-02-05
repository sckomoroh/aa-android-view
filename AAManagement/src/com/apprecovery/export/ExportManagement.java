package com.apprecovery.export;

import com.apprecovery.BaseManagement;
import com.communicator.ManagementMethod;
import com.communicator.ManagementService;

/**
 * Created by sckomoroh
 * 2/5/2015.
 */
@ManagementService(prefix = "export/schedule")
public class ExportManagement extends BaseManagement
{
    public ExportManagement()
    {
        createInvoker();
    }

    @ManagementMethod(urlTemplate = "agents/allExportConfig")
    public AgentExportConfigurationCollection getAllAgentsExportConfiguration() throws Exception
    {
        return invoker.invokeManagementMethodGet(null);
    }
}
