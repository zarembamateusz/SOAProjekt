package service;

import dao.EventDao;
import entity.EventEntity;
import io.vavr.Tuple2;
import jms.EventType;
import mappers.EventMapper;
import models.CarPlace;
import models.User;
import models.service.UserService;
import models.service.ZoneService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class DetectionService {

    ZoneService zoneService = new ZoneServiceImpl();

    EventDao eventDao = EventDao.create();

    private final long checkTime = 1000 * 60;

    @PostConstruct
    public void init() {
        new Thread(this::checkIfTimeIsExpired).start();
        new Thread(this::checkIfTicketWasBought).start();
    }

    private void checkIfTicketWasBought() {
        try {
            Thread.sleep(checkTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eventDao.getAll()
                .stream()
                .filter(eventEntity -> eventEntity.getType().equals(EventType.CAR_IN))
                .filter(eventEntity -> eventEntity.getTimeOfCreated().isBefore(LocalDateTime.now().minusMinutes(2)))
                .filter(eventEntity -> !zoneService.getByCarId(eventEntity.getCarPlaceId()).haveTicket())
                .peek(eventEntity -> eventEntity.setType(EventType.TICKET_NOT_BOUGHT))
                .forEach(eventDao::update);
    }


    private void checkIfTimeIsExpired() {
        try {
            Thread.sleep(checkTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        zoneService.getAll()
                .stream()
                .map(zone -> new Tuple2<>(zone.getWorkers(), zone.getPlaces().stream()
                        .filter(CarPlace::isTicketExpired)
                        .map(CarPlace::getId)
                        .collect(Collectors.toSet())))
                .map(t -> t._1.stream()
                        .map(user -> t._2.stream()
                                .map(place -> createEvent(user, place))
                                .collect(Collectors.toSet()))
                        .collect(Collectors.toSet()))
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .peek(eventEntity -> eventDao.deleteByCarPlaceId(eventEntity.getCarPlaceId()))
                .forEach(eventDao::create);
    }

    private EventEntity createEvent(final String user, final String carPlace) {
        return EventEntity.builder()
                .userId(user)
                .type(EventType.TICKET_EXPIRED)
                .carPlaceId(carPlace)
                .id(UUID.randomUUID().toString())
                .build();
    }
}
