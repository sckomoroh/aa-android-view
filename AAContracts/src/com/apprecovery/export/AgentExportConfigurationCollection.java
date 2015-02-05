package com.apprecovery.export;

import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by administrator on 2/5/2015.
 */
@ContractXmlType(elementName = "agentsExportConfiguration", isCollection = true, itemName = "exportConfig", isCollectionPrimitive = false)
public class AgentExportConfigurationCollection
        extends ArrayList<AgentExportConfiguration>
        implements Serializable {
}
