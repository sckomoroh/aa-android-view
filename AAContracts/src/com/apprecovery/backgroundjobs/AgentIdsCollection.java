package com.apprecovery.backgroundjobs;

import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sckomoroh on 12.10.2014.
 */
@ContractXmlType(elementName = "agentIds", isCollection = true, isCollectionPrimitive = true, itemName = "agentId")
public class AgentIdsCollection extends ArrayList<String> implements Serializable
{
}
