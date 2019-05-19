import Models.Zone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "DashboardBean")
@SessionScoped
public class DashboardBean {
    //TODO delete test data and configure other data source
    private List<Zone> zoneList = new ArrayList<Zone>(){{
        add(new Zone(1,15,15));
        add(new Zone(2,20,18));
        add(new Zone(3,53,50));
        add(new Zone(4,60,52));
    }};

    public List<Zone> getZoneList() {
        return new ArrayList<Zone>(){{
            add(new Zone(1,15,15));
            add(new Zone(2,20,18));
            add(new Zone(3,53,50));
            add(new Zone(4,60,52));
        }};
    }

    public void setZoneList(List<Zone> zoneList) {
        this.zoneList = zoneList;
    }
}
