package app.view;


import jms.Event;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface RestService {


    @Path("events")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<Event> getAll();

    @Path("events/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<Event> getAllClientEvents(@PathParam("id") String id);
}
