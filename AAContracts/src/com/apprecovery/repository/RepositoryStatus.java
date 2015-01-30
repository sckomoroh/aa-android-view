package com.apprecovery.repository;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/23/13
 * Time: 8:48 PM
 * To change this template use File | Settings | File Templates.
 */
public enum RepositoryStatus
{
    Unknown,
    Unmounting,
    Unmounted,
    Mounting,
    Mounted,
    Maintenance,
    Error
}
