package beans;


import lombok.Getter;
import lombok.Setter;
import models.User;
import models.Zone;
import models.service.ZoneService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "ZoneBean")
@SessionScoped
public class ZoneBean implements Serializable {

    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/ZoneServiceImpl!models.service.ZoneService")
    private ZoneService zoneService;

    @Getter
    @Setter
    public List<User> users = new ArrayList<>();

    public void addNewZone(final String code, final int number, final List<User> responsibleUser) {

        zoneService.create(code, number, responsibleUser);
    }

    public Zone findZoneById(final String id) {
        return zoneService.findById(id);
    }

    public List<Zone> getAllZones() {
        return zoneService.getAll();
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/zone.xhtml";
    }


}
