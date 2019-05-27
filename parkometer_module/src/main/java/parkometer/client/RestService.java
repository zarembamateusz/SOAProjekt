package parkometer.client;


import models.CarPlace;
import models.Zone;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;

public interface RestService {


    @Path("zones/{zone_id}/{place_id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void putNewTicket(@PathParam("zone_id") String zoneId,
                      @PathParam("place_id") String placeId,
                       String endTime);


    @Path("zones")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<Zone> getAllZones();

    @Path("zones/{zone_id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<CarPlace> getAllPlacesInZone(@PathParam("zone_id") String zoneId);
}
