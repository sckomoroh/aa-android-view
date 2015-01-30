package com.apprecovery.backgroundjobs;

import com.serialization.ContractXmlType;

import java.util.ArrayList;

/**
 * Created by sckomoroh on 28/01/2015.
 */
@ContractXmlType(elementName = "backgroundJobTypes", isCollection = true, isCollectionPrimitive = false, itemName = "BackgroundJobType")
public class BackgroundJobTypeCollection extends ArrayList<BackgroundJobType>
{
}
