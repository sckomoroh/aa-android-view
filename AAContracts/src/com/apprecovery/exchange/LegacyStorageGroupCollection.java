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

@ContractXmlType(elementName = "legacyStorageGroups", isCollection = true, itemName = "rpLegacyStorageGroup", isCollectionPrimitive = false)
public class LegacyStorageGroupCollection extends ArrayList<LegacyStorageGroup> implements Serializable
{
    public int getState()
    {
        for (LegacyStorageGroup legacyStorageGroup : this)
        {
            if (legacyStorageGroup.getState() == VolumeImageSummary.RED)
            {
                return VolumeImageSummary.RED;
            }

            if (legacyStorageGroup.getState() == VolumeImageSummary.YELLOW)
            {
                return VolumeImageSummary.YELLOW;
            }
        }

        return VolumeImageSummary.GREEN;
    }
}
