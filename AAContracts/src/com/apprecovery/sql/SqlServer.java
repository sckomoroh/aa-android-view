package com.apprecovery.sql;

import com.serialization.ContractXmlMethod;
import com.serialization.ContractXmlType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 7:34 PM
 */

@ContractXmlType(elementName = "sqlServer")
public class SqlServer implements Serializable
{
    private SqlInstanceCollection instances;

    @ContractXmlMethod(elementName = "instances")
    public SqlInstanceCollection getInstances()
    {
        return instances;
    }

    @ContractXmlMethod(elementName = "instances", isSetter = true)
    public void setInstances(SqlInstanceCollection instances)
    {
        this.instances = instances;
    }
}
