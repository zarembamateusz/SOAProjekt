import rest.TestData;
import models.Zone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "DashboardBean")
@SessionScoped
public class DashboardBean {
    //TODO delete test data and configure other data source
    private List<Zone> zoneList = new ArrayList<Zone>();

    public List<Zone> getZoneList() {
        zoneList=TestData.zoneList;
        return zoneList;
    }

    public void setZoneList(List<Zone> zoneList) {
        this.zoneList = zoneList;
    }
}
