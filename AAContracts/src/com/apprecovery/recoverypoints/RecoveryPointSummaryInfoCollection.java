package com.apprecovery.recoverypoints;

import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */

@ContractXmlType(elementName = "recoveryPointSummaries", isCollection = true, isCollectionPrimitive = false, itemName = "recoveryPointSummary")
public class RecoveryPointSummaryInfoCollection extends ArrayList<RecoveryPointSummaryInfo> implements Serializable
{
}
