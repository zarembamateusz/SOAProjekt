package beans;


import lombok.Getter;
import models.Role;
import models.User;
import models.service.UserService;
import util.PasswordUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@ManagedBean(name = "PasswordsBean")
@SessionScoped
public class PasswordsBean {

    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/UserServiceImpl!models.service.UserService")
    private UserService userService;

    private List<User> userList = new ArrayList<>();
    private User selectedUser = new User();
    private String myPasswd = "";

    @Getter
    private User currentUser;

    @PostConstruct
    public void init() {
       currentUser = userService.getAll().stream()
                .filter(user -> user.getLogin().equals(FacesContext.getCurrentInstance().getExternalContext()
                        .getUserPrincipal()
                        .getName()))
                .findFirst()
                .get();
       if (!checkIfUserIsAdmin()) selectedUser = currentUser;
    }


    public boolean checkIfUserIsAdmin () {
        return currentUser.getRole().equals(Role.Manager);
    }
    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }


    public String getMyPasswd() {
        return myPasswd;
    }

    public void setMyPasswd(String myPasswd) {
        this.myPasswd = myPasswd;
    }

    public List<User> getUserList() {
        if (currentUser.getRole() == Role.Manager)
            return userService.getAll();
        else
            return new ArrayList<User>() {{
                add(currentUser);
            }};
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void update(){

        String encodedBytes = PasswordUtil.encode(myPasswd);

        if(encodedBytes.equals(currentUser.getPassword())){
            selectedUser.setPassword(PasswordUtil.encode(selectedUser.getPassword()));
            userService.update(selectedUser);

        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/passwords.xhtml";
    }
}
