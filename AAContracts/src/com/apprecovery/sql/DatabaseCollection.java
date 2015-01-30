package com.apprecovery.sql;

import com.apprecovery.databasecheck.CheckFlags;
import com.apprecovery.volumeimage.VolumeImageSummary;
import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 7:18 PM
 */

@ContractXmlType(elementName = "databases", isCollection = true)
public class DatabaseCollection extends ArrayList<Database> implements Serializable
{
    public int getState()
    {
        for (Database database : this)
        {
            if ((database.getCheckResults().getCheckResults() & CheckFlags.AttachabilityCheckPassed) == 0)
            {
                return VolumeImageSummary.YELLOW;
            }

            if ((database.getCheckResults().getCheckResults() & CheckFlags.AttachabilityCheckFailed) != 0)
            {
                return VolumeImageSummary.RED;
            }
        }

        return VolumeImageSummary.GREEN;
    }
}
