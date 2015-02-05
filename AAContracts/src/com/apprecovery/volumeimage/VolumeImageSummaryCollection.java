package com.apprecovery.volumeimage;

import com.apprecovery.agents.AgentInfoSummary;
import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/29/13
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */

@ContractXmlType(elementName = "volumeImages", isCollection = true, isCollectionPrimitive = false, itemName = "volumeImageSummary")
public class VolumeImageSummaryCollection extends ArrayList<VolumeImageSummary> implements Serializable
{
}
