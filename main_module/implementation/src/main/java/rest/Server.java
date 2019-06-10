package rest;

import rest.controller.EventController;
import rest.controller.ZoneController;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
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
