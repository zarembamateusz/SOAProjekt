package service;

import dao.EventDao;
import jms.Event;
import jms.EventType;
import jms.service.JMSService;
import models.CarPlace;
import models.Zone;
import models.service.EventService;
import models.service.UserService;
import models.service.ZoneService;
import org.apache.log4j.Logger;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Singleton
@PermitAll
@SecurityDomain("test-policy")
public class DetectionService {
    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/JMSServiceImpl!jms.service.JMSService")
    JMSService topic;
    private final ZoneService zoneService = new ZoneServiceImpl();
    private EventService eventService = new EventServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final static EventDao eventDao = EventDao.create();
    private final static Logger logger = Logger.getLogger(DetectionService.class);
    private final long checkTime = 1000;

    @Schedule(minute = "*/1", hour = "*", persistent = false)
    private void foundAlert() {
        ZoneServiceImpl zs = new ZoneServiceImpl();
        for(Zone z : zs.getAll()) {
            for (CarPlace cp :z.getPlaces()) {
                Date date = new Date();
                long time = date.getTime();//time to milisecundy
                Timestamp ts = new Timestamp(time);
                if (cp.getCurrentTicket().getEndTime().isAfter(LocalDateTime.now())) {
                    Event event = Event.builder()
                            .carCode(cp.getCode())
                            .carPlaceId(cp.getId())
                            .zoneCode(z.getCode())
                            .zoneId(z.getId())
                            .type(EventType.NEED_TO_GO)
                            .description("Brak waznego biletu. Idz wystawic mandat !")
                            .build();
                    eventService.carIn(event);
                    topic.sendTopic(event);
                }
            }
        }
    }


//    private EventEntity createEvent(final String user, final String carPlace, final String zoneId) {
//        return EventEntity.builder()
//                .userId(user)
//                .type(EventType.TICKET_EXPIRED)
//                .carPlaceId(carPlace)
//                .timeOfCreated(LocalDateTime.now())
//                .zoneId(zoneId)
//                .id(UUID.randomUUID().toString())
//                .build();
//    }
}
