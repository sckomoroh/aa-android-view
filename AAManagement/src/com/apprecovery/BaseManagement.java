package com.apprecovery;

import com.apprecovery.config.AACoreConfig;
import com.communicator.Invoker;

/**
 * Created with IntelliJ IDEA.
 * User: sckomoroh
 * Date: 5/12/13
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseManagement
{
    protected Invoker invoker;

    protected void createInvoker()
    {
        invoker = new Invoker(
                AACoreConfig.getInstance().getServiceUrl(),
                AACoreConfig.getInstance().getUserName(),
                AACoreConfig.getInstance().getDomain(),
                AACoreConfig.getInstance().getPassword());
    }
}
