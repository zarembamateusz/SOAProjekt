package service;

import dao.EventDao;
import dao.UserDao;
import entity.EventEntity;
import io.reactivex.Observable;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import jms.EventType;
import mappers.EventMapper;
import models.CarPlace;
import models.User;
import models.service.UserService;
import models.service.ZoneService;
import org.apache.log4j.Logger;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Singleton
@PermitAll
@SecurityDomain("test-policy")
public class DetectionService {

    private final ZoneService zoneService = new ZoneServiceImpl();

    private final UserService userService = new UserServiceImpl();
    private final static EventDao eventDao = EventDao.create();
    private final static Logger logger = Logger.getLogger(DetectionService.class);
    private final long checkTime = 1000;

    @Schedule(minute = "*/1", hour = "*", persistent = false)
    private void checkIfTicketWasBought() {
//        eventDao.getAll()
//                .stream()
//                .peek(logger::info)
//                .filter(eventEntity -> eventEntity.getType().equals(EventType.CAR_IN))
//                .peek(logger::info)
//                .filter(eventEntity -> eventEntity.getTimeOfCreated().isBefore(LocalDateTime.now().minusMinutes(2)))
//                .peek(logger::info)
//                .filter(eventEntity -> !zoneService.getByCarId(eventEntity.getCarPlaceId()).haveTicket())
//                .peek(eventEntity -> eventEntity.setType(EventType.TICKET_NOT_BOUGHT))
//                .peek(logger::info)
//                .forEach(eventDao::update);
    }


    @Schedule(minute = "*/1", hour = "*", persistent = false)
    private void checkIfTimeIsExpired() {

        userService.getAll()
                .forEach(logger::info);
//        zoneService.getAll()
//                .stream()
//                .map(zone -> new Tuple3<>(zone.getWorkers(), zone.getPlaces().stream()
//                        .filter(CarPlace::isTicketExpired)
//                        .map(CarPlace::getId)
//                        .collect(Collectors.toSet())
//                ,zone.getId()))
//                .map(t -> t._1.stream()
//                        .map(user -> t._2.stream()
//                                .map(place -> createEvent(user, place,t._3))
//                                .collect(Collectors.toSet()))
//                        .collect(Collectors.toSet()))
//                .peek(e-> logger.info(" expired " + e))
//                .flatMap(Collection::stream)
//                .flatMap(Collection::stream)
//                .peek(eventEntity -> eventDao.deleteByCarPlaceId(eventEntity.getCarPlaceId()))
//                .peek(logger::info)
//                .forEach(eventDao::create);
    }

    private EventEntity createEvent(final String user, final String carPlace, final String zoneId) {
        return EventEntity.builder()
                .userId(user)
                .type(EventType.TICKET_EXPIRED)
                .carPlaceId(carPlace)
                .zoneId(zoneId)
                .id(UUID.randomUUID().toString())
                .build();
    }
}
