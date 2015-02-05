package com.apprecovery.sql;

import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates.
 */

@ContractXmlType(elementName = "fileGroups", isCollection = true)
public class DatabaseFileGroupCollection extends ArrayList<DatabaseFileGroup> implements Serializable
{
}
