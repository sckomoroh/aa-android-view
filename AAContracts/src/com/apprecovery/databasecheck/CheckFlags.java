package com.apprecovery.databasecheck;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 4/30/13
 * Time: 8:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class CheckFlags implements Serializable
{
    public static final int None = 0;
    public static final int AttachabilityCheckPassed = 1;
    public static final int MountCheckPassed = 2;
    public static final int MountCheckFailed = 4;
    public static final int ChecksumCheckPassed = 8;
    public static final int AttachabilityCheckFailed = 16;
}
