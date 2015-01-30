package com.apprecovery.agents;

import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/29/13
 * Time: 5:39 PM
 */

@ContractXmlType(elementName = "agentInfoSummaries", isCollection = true, itemName = "agentInfoSummary", isCollectionPrimitive = false)
public class AgentInfoSummaryCollection
        extends ArrayList<AgentInfoSummary>
        implements Serializable
{
}
