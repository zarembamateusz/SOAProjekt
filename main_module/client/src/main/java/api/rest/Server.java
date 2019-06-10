package api.rest;

import api.rest.controller.EventController;
import api.rest.controller.ZoneController;
import models.CarPlace;
import models.Zone;
import models.service.ZoneService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationPath("/api/rest")
public class Server extends Application {
    @Override
    public Set<Class<?>> getClasses() {

        return Stream.of(ZoneController.class, EventController.class)
                .collect(Collectors.toSet());
    }
}
