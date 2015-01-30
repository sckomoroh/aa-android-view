package com.apprecovery.repository;

import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/23/13
 * Time: 9:08 PM
 */

@ContractXmlType(elementName = "repositorySummaries", isCollection = true, isCollectionPrimitive = false, itemName = "repositorySummary")
public class RepositorySummaryInfoCollection extends ArrayList<RepositorySummaryInfo> implements Serializable
{
}
