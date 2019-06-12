package beans;

import models.Role;
import models.User;
import models.Zone;
import models.service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static models.Role.*;

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
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (userService == null) {
            System.out.println("");
        }
        return userService.getWorkers();
    }

    public void createUser(final String login, final String password, final String name,
                           final String lastName, final String role, final Zone zone) {


        userService.createUser(login, password, name, lastName,role.equals("ADMIN")? Manager : WORKER, Optional.ofNullable(zone)
                .map(Zone::getId)
                .orElse(null));
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/user.xhtml";
    }
}
