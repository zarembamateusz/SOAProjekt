package api.rest.controller;

import jms.Event;
import models.service.EventService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/events")
public class EventController {

    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/EventServiceImpl!models.service.EventService")
    private EventService eventService;

    @Path("")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllZones() {
        return eventService.getAll();
    }

    @Path("/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllClientEvents(@PathParam("id") String id) {
        return eventService.findAllUsersEvent(id);
    }
}
