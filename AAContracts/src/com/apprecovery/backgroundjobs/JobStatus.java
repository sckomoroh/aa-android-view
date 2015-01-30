package com.apprecovery.backgroundjobs;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/25/13
 * Time: 12:21 AM
 * To change this template use File | Settings | File Templates.
 */
public enum JobStatus
{
    Unknown,
    Starting, // This might be too fine-grained to be worth implementing.
    Running,
    Completing,
    Succeeded,
    Failed,
    Canceled,
    Waiting, // If job is waiting for user response
    Pending,  // if job was created but has not started yet
    Queued
}
