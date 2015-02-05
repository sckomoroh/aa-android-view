package com.apprecovery.repository;

import com.serialization.ContractXmlType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/23/13
 * Time: 8:54 PM
 */

@ContractXmlType(elementName = "files", isCollection = true, isCollectionPrimitive = false, itemName = "reposFileInfo")
public class RepositoryFileInfoCollection extends ArrayList<RepositoryFileInfo> implements Serializable
{
}
