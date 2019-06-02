package models.service;

import models.Role;
import models.User;
import models.Zone;

import java.util.List;

public interface UserService extends GenericService<User, String> {

    List<User> getAdmins();

    List<User> getByZoneId(String zoneId);

    List<User> getWorkers();

    void createUser(final String login, final String password, final String name,
                    final String lastName, final Role role, final String zoneId);
}
