package api.rest.controller;

import models.CarPlace;
import models.Zone;
import models.service.ZoneService;
import org.jboss.resteasy.annotations.Body;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Path("/zones")
public class ZoneController {


    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/ZoneServiceImpl!models.service.ZoneService")
    private ZoneService zoneService;

    @Path("/{zone_id}/{place_id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void putNewTicket(@PathParam("zone_id") String zoneId,
                             @PathParam("place_id") String placeId,
                              String endTime) {

        zoneService.reserve(zoneId, placeId, LocalDateTime.parse(endTime));

    }

    @Path("")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Zone> getAllZones() {
        return zoneService.getAll();
    }

    @Path("/{zone_id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarPlace> getAllPlacesInZone(@PathParam("zone_id") String zoneId) {
        return zoneService.getAll()
                .stream()
                .filter(zone -> zone.getId().equals(zoneId))
                .map(Zone::getPlaces)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
