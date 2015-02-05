package com.apprecovery.backgroundjobs;

import com.apprecovery.events.EventSummary;
import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/25/13
 * Time: 12:19 AM
 */

@ContractXmlType(elementName = "jobs", isCollection = true, isCollectionPrimitive = false, itemName = "job")
public class BackgroundJobInfoCollection extends ArrayList<BackgroundJobInfo> implements Serializable
{
}
