package com.apprecovery.exchange;

import com.apprecovery.databasecheck.CheckFlags;
import com.apprecovery.volumeimage.VolumeImageSummary;
import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 5/16/13
 * Time: 1:56 PM
 */

@ContractXmlType(elementName = "legacyMailStores", isCollection = true, itemName = "rpLegacyMailStore", isCollectionPrimitive = false)
public class LegacyMailStoreCollection extends ArrayList<LegacyMailStore> implements Serializable
{
    public int getState()
    {
        for (LegacyMailStore legacyMailStore : this)
        {
            if ((legacyMailStore.getCheckResults().getCheckResults() & CheckFlags.MountCheckFailed) != 0)
            {
                return VolumeImageSummary.RED;
            }

            if ((legacyMailStore.getCheckResults().getCheckResults() & CheckFlags.MountCheckPassed) == 0)
            {
                return VolumeImageSummary.YELLOW;
            }
        }

        return VolumeImageSummary.GREEN;
    }
}
