package beans;

import lombok.Getter;
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
import java.util.*;
import java.util.stream.Collectors;



@ManagedBean(name = "DashboardBean")
@SessionScoped
public class DashboardBean implements Serializable {

    private User currentUser;


    @Getter
    private Map<CarPlace,String>  carPlaceMap = new HashMap<>();
    @PostConstruct
    public void init() {
        val principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        currentUser = userService.getAll().stream()
                .filter(user -> user.getLogin().equals(principal.getName()))
                .findFirst()
                .get();
    }

    //TODO delete test data and configure other data source
    private List<CarPlace> carPlaces = new ArrayList<CarPlace>();
    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/ZoneServiceImpl!models.service.ZoneService")
    private ZoneService zoneService;
    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/UserServiceImpl!models.service.UserService")
    private UserService userService;


    public List<CarPlace> getCarPlaces() {
        if (currentUser.getRole() == Role.Manager) {
            for(Zone z : zoneService.getAll()){
                for (CarPlace cp : z.getPlaces()) {
                    carPlaceMap.put(cp, z.getCode());
                    carPlaces.add(cp);

                }

            }
        }else {
            for(Zone z : currentUser.getZones().stream().map(zoneService::findById)
                    .collect(Collectors.toList())){
                for (CarPlace cp : z.getPlaces()) {
                    carPlaceMap.put(cp, z.getCode());
                    carPlaces.add(cp);
                }

            }
        }

        return carPlaces;
    }

    public String getCarPlaceStatus(CarPlace carPlace){
        if(carPlace.getStatus() == 1)
            return "ZAJĘTE";
        else
            return "WOLNE";

    }

    public String getExpiringTime(CarPlace carPlace){
        if(carPlace.haveTicket())
            return carPlace.getCurrentTicket().getEndTime().toString();
        else
            return "Brak biletu";
    }




    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/dashboard.xhtml";
    }
}
