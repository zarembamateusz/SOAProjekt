package beans;


import models.Role;
import models.User;
import models.service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "PasswordsBean")
@SessionScoped
public class PasswordsBean {

    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/UserServiceImpl!models.service.UserService" )
    private UserService userService;

    private List<User> userList = new ArrayList<>();
    private User selectedUser = new User();
    private String myPasswd = "";

    private User currentUser = userService.getAll().stream()
            .filter(user -> user.getLogin().equals(FacesContext.getCurrentInstance().getExternalContext()
                    .getUserPrincipal()
                    .getName()))
            .findFirst()
            .get();


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
        if(currentUser.getRole()==Role.MANAGER)
            return userService.getAll();
        else
            return new ArrayList<User>(){{
                add(currentUser);}};
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
