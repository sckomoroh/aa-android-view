package com.apprecovery.exchange;

import com.apprecovery.databasecheck.CheckFlags;
import com.apprecovery.volumeimage.VolumeImageSummary;
import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: sckomoroh
 * Date: 5/12/13
 * Time: 1:14 PM
 */

@ContractXmlType(elementName = "mailStores", isCollection = true, isCollectionPrimitive = false, itemName = "rpMailStore")
public class MailStoreCollection extends ArrayList<MailStore> implements Serializable
{
    public int getState()
    {
        for (MailStore mailStore : this)
        {
            if ((mailStore.getCheckResults().getCheckResults() & CheckFlags.MountCheckFailed) != 0)
            {
                return VolumeImageSummary.RED;
            }

            if ((mailStore.getCheckResults().getCheckResults() & CheckFlags.MountCheckPassed) == 0)
            {
                return VolumeImageSummary.YELLOW;
            }
        }

        return VolumeImageSummary.GREEN;
    }
}
