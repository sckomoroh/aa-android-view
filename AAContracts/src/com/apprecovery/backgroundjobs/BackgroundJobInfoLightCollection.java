package com.apprecovery.backgroundjobs;

import com.serialization.ContractXmlType;

import java.util.ArrayList;

/**
 * Created by sckomoroh on 13.10.2014.
 */

@ContractXmlType(elementName = "jobLightList", isCollection = true, isCollectionPrimitive = false, itemName = "jobLight")
public class BackgroundJobInfoLightCollection extends ArrayList<BackgroundJobInfoLight>
{
}
