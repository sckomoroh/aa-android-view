package com.apprecovery.metadata;

import com.apprecovery.BaseManagement;
import com.communicator.ManagementMethod;
import com.communicator.ManagementService;

/**
 * Created with IntelliJ IDEA.
 * User: sckomoroh
 * Date: 5/12/13
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagementService(prefix = "metadata")
public class MetadataManagement extends BaseManagement
{
    public MetadataManagement()
    {
        createInvoker();
    }

    @ManagementMethod(urlTemplate = "summary")
    public CoreSummaryMetadata getSummaryMetadata() throws Exception
    {
        return invoker.invokeManagementMethodGet(null);
    }
}
