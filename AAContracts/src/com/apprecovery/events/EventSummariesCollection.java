package com.apprecovery.events;

import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/24/13
 * Time: 11:57 PM
 */

@ContractXmlType(elementName = "eventSummaries", isCollection = true, isCollectionPrimitive = false, itemName = "eventSummary")
public class EventSummariesCollection extends ArrayList<EventSummary> implements Serializable
{
}
