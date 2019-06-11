package api.soap;

import jms.Event;
import jms.EventType;
import jms.service.JMSService;
import lombok.val;
import lombok.var;
import models.CarPlace;
import models.Zone;
import models.service.ZoneService;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
        Zone zone = zoneService.findById(event.getZoneId());
        CarPlace carPlace = null;
        for(CarPlace cp :zone.getPlaces())
            if(cp.getId().equals(event.getCarPlaceId()))
                carPlace = cp;


        if(carPlace == null) return;
        val newEndTime = ZonedDateTime.now().plusSeconds(45);
        if(event.getType()==EventType.CAR_IN) {

            var addTicket = false;
            if(carPlace.haveTicket() && carPlace.getCurrentTicket().getEndTime().isAfter(newEndTime.toLocalDateTime()))
                addTicket = true;
            zoneService.reserveWithChangeStatus(zone.getId(),event.getCarPlaceId(),LocalDateTime.now(),1,addTicket);

        }else
            zoneService.reserveWithChangeStatus(zone.getId(),event.getCarPlaceId(),newEndTime.toLocalDateTime(),0,false);


        
        




//
//        topic.sendTopic(event);
    }

    @Override
    public Zone getZoneByCode(String code) {
        return zoneService.getZoneByCode(code);
    }
}
