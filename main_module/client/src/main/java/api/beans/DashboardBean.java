package api.beans;

import api.rest.TestData;
import lombok.val;
import models.CarPlace;
import models.User;
import models.Zone;
import models.service.UserService;
import models.service.ZoneService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "DashboardBean")
@SessionScoped
public class DashboardBean implements Serializable {
    //TODO delete test data and configure other data source
    private List<Zone> zoneList = new ArrayList<Zone>();
    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/ZoneServiceImpl!models.service.ZoneService")
    private ZoneService zoneService;
    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/UserServiceImpl!models.service.UserService" )
    private UserService userService;

    public List<Zone> getZoneList() {
        zoneList = TestData.zoneList;
        val user = User.builder().build();
        val zone = Zone.builder().build();
//        userService.create(user);
        zoneService.create(zone);
        return zoneList;
    }

    public int getNumberOfBuyTicket(String id){
        int buyTicket = 0;
            for (Zone z : zoneList)
                if(z.getId().equals(id))
                    for (CarPlace cp : z.getPlaces())
                        if (cp.haveTicket())
                            buyTicket++;

        return buyTicket;
    }

    public void setZoneList(List<Zone> zoneList) {
        this.zoneList = zoneList;
    }
}
