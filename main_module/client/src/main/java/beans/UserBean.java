package beans;

import lombok.val;
import models.Role;
import models.User;
import models.Zone;
import models.service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@ManagedBean(name = "UserBean")
@SessionScoped
public class UserBean implements Serializable {

    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/UserServiceImpl!models.service.UserService")
    private UserService userService;

    public User getUserById(final String id) {
        return userService.findById(id);
    }

    public List<User> getAllUsers() {
        return userService.getAll();
    }

    public List<User> getAdmins() {
        return userService.getAdmins();
    }

    public List<User> getWorkers() {
        return userService.getWorkers();
    }

    public void createUser(final String login, final String password, final String name,
                           final String lastName, final String role, final Zone zone) {


        userService.createUser(login, password, name, lastName, Role.valueOf(role), Optional.ofNullable(zone)
                .map(Zone::getId)
                .orElse(null));
    }
}
