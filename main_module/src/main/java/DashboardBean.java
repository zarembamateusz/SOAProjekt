import lombok.val;
import models.User;
import rest.TestData;
import models.Zone;
import service.UserService;
import service.ZoneService;

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
    private ZoneService zoneService =ZoneService.create();
    private UserService userService = UserService.create();

    public List<Zone> getZoneList() {
        zoneList=TestData.zoneList;
        val user = User.builder().build();
        val zone = Zone.builder().build();
        userService.create(user);
        zoneService.create(zone);
        return zoneList;
    }

    public void setZoneList(List<Zone> zoneList) {
        this.zoneList = zoneList;
    }
}
