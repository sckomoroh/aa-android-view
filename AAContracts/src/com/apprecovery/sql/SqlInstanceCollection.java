package com.apprecovery.sql;

import com.apprecovery.volumeimage.VolumeImageSummary;
import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 7:33 PM
 */

@ContractXmlType(elementName = "instances", isCollection = true)
public class SqlInstanceCollection extends ArrayList<SqlInstance> implements Serializable
{
    public int getState()
    {
        for (SqlInstance sqlInstance : this)
        {
            if (sqlInstance.getDatabases().getState() == VolumeImageSummary.RED)
            {
                return VolumeImageSummary.RED;
            }

            if (sqlInstance.getDatabases().getState() != VolumeImageSummary.GREEN)
            {
                return VolumeImageSummary.YELLOW;
            }
        }

        return VolumeImageSummary.GREEN;
    }
}
