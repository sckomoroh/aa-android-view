package com.apprecovery.events;

import com.apprecovery.BaseManagement;
import com.communicator.ManagementMethod;
import com.communicator.ManagementService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ezvigunov
 * Date: 9/24/13
 * Time: 11:59 PM
 */

@ManagementService(prefix = "events")
public class EventsManagement extends BaseManagement
{
    public EventsManagement()
    {
        createInvoker();
    }

    @ManagementMethod(urlTemplate = "core/eventsCount")
    public long getCoreEventCount() throws Exception
    {
        return invoker.invokeManagementMethodGet(null);
    }

    @ManagementMethod(urlTemplate = "agents/{agentId}/eventsCount")
    public long getAgentEventCount(String agentId) throws Exception
    {
        Map<String, String> args = new HashMap<>();
        args.put("{agentId}", agentId);

        return invoker.invokeManagementMethodGet(args);
    }

    @ManagementMethod(urlTemplate = "core/paged?max={max}&page={page}")
    public EventSummariesCollection getCoreEventsByPage(Integer max, Integer page) throws Exception
    {
        Map<String, String> args = new HashMap<>();
        args.put("{max}", max.toString());
        args.put("{page}", page.toString());

        return invoker.invokeManagementMethodGet(args);
    }

    @ManagementMethod(urlTemplate = "agents/{agentId}/paged?max={max}&page={page}")
    public EventSummariesCollection getAgentEventsByPage(String agentId, Integer max, Integer page) throws Exception
    {
        Map<String, String> args = new HashMap<>();
        args.put("{agentId}", agentId);
        args.put("{max}", max.toString());
        args.put("{page}", page.toString());

        return invoker.invokeManagementMethodGet(args);
    }
}
