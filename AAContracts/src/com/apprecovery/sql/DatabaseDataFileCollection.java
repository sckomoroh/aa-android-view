package com.apprecovery.sql;

import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */

@ContractXmlType(elementName = "dataFiles", isCollection = true)
public class DatabaseDataFileCollection extends ArrayList<DatabaseDataFile> implements Serializable
{
}
