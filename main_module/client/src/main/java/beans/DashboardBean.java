package beans;

import models.CarPlace;
import models.Role;
import models.User;
import models.Zone;
import models.service.UserService;
import models.service.ZoneService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@ManagedBean(name = "DashboardBean")
@SessionScoped
public class DashboardBean implements Serializable {


   public DashboardBean(){
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
//           List<User> c = userService.getAll();
    }
    //TODO delete test data and configure other data source
    private List<Zone> zoneList = new ArrayList<Zone>();
    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/ZoneServiceImpl!models.service.ZoneService")
    private ZoneService zoneService;
    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/UserServiceImpl!models.service.UserService" )
    private UserService userService;

    private User currentUser = userService.getAll().stream()
            .filter(user -> user.getLogin().equals(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName()))
            .findFirst()
            .get();

    public List<Zone> getZoneList() {
        if(currentUser.getRole()==Role.MANAGER)
            zoneList = zoneService.getAll();
        else {
            //TODO showing zone for worker
        }
//        zoneList = TestData.zoneList;
//        val user = User.builder().build();
//        val zone = Zone.builder().build();
//        userService.create(user);
//        zoneService.create(zone);
        return zoneList;
    }


    public long getNumberOfBuyTicket(String id){
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
}
