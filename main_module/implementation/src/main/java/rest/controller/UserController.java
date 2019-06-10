package rest.controller;


import jms.Event;
import models.User;
import models.service.UserService;
import service.UserServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserController {

    private UserService userService = new UserServiceImpl();

    @Path("/{login}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByLogin(@PathParam("login") String login) {
        return userService.getByLogin(login);
    }
}
