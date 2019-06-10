package app.view;


import jms.Event;
import models.CarPlace;
import models.Zone;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface RestService {


    @Path("events")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<Event> getAll();

}
