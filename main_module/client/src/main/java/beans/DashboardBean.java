package beans;

import lombok.val;
import models.CarPlace;
import models.Role;
import models.User;
import models.Zone;
import models.service.UserService;
import models.service.ZoneService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;



@ManagedBean(name = "DashboardBean")
@SessionScoped
public class DashboardBean implements Serializable {

    private User currentUser;

    @PostConstruct
    public void init() {
        val principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        currentUser = userService.getAll().stream()
                .filter(user -> user.getLogin().equals(principal.getName()))
                .findFirst()
                .get();
    }

    //TODO delete test data and configure other data source
    private List<Zone> zoneList = new ArrayList<Zone>();
    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/ZoneServiceImpl!models.service.ZoneService")
    private ZoneService zoneService;
    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/UserServiceImpl!models.service.UserService")
    private UserService userService;


    public List<Zone> getZoneList() {
        if (currentUser.getRole() == Role.Manager)
            zoneList = zoneService.getAll();
        else {
            zoneList = currentUser.getZones().stream().map(zoneService::findById)
                    .collect(Collectors.toList());
        }

        return zoneList;
    }


    public long getNumberOfBuyTicket(String id) {
        return zoneList.stream()
                .filter(zone -> zone.getId().equals(id))
                .map(Zone::getPlaces)
                .flatMap(Collection::stream)
                .filter(CarPlace::haveTicket)
                .count();
    }

    public void setZoneList(List<Zone> zoneList) {
        this.zoneList = zoneList;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/dashboard.xhtml";
    }
}
