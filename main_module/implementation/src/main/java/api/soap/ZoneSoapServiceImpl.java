package api.soap;

import jms.Event;
import jms.EventType;
import jms.service.JMSService;
import models.CarPlace;
import models.Zone;
import models.service.ZoneService;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "api.soap.ZoneSoapService")
public class ZoneSoapServiceImpl implements ZoneSoapService {

    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/ZoneServiceImpl!models.service.ZoneService")
    private ZoneService zoneService;

    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/JMSServiceImpl!jms.service.JMSService")
    JMSService topic;

    @WebMethod
    @Override
    public List<Zone> getAllZones() {
        return zoneService.getAll();
    }

    @WebMethod
    @Override
    public void action(Event event) {

        
        




//
        topic.sendTopic(event);
    }

    @Override
    public Zone getZoneByCode(String code) {
        return zoneService.getZoneByCode(code);
    }
}
