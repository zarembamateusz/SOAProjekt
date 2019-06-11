package service;

import dao.EventDao;
import dao.UserDao;
import dao.ZoneDao;
import entity.UserEntity;
import jms.Event;
import jms.service.Observer;
import models.service.EventService;
import lombok.val;
import mappers.EventMapper;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.security.PermitAll;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Remote(EventService.class)
@Stateless
@PermitAll
@SecurityDomain("test-policy")
public class EventServiceImpl implements EventService, Serializable {

    private final ZoneDao zoneDao = ZoneDao.create();
    private final EventDao eventDao = EventDao.create();
    private final UserDao userDao = UserDao.create();

    @Inject
    public javax.enterprise.event.Event<String> events;


    @Override
    public List<Event> findAllUsersEvent(String userId) {
        return eventDao
                .findAllUserEvents(userId)
                .stream()
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findAllUnreadeEvent(String userId) {
        return eventDao
                .findAllUserEvents(userId)
                .stream()
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void notifyObservers(Event event) {
        events.fire(event.getZoneId());
    }


    @Override
    public List<Event> getAll() {
        return eventDao.getAll().stream().map(EventMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void carIn(Event event) {
        userDao.findByZoneId(event.getZoneId())
                .stream()
                .map(UserEntity::getId)
                .map(id-> EventMapper.toEntity(event,id))
                .forEach(eventDao::create);
    }

    @Override
    public void carOut(Event event) {
        val zone = zoneDao.findById(event.getZoneId());
        zone.getSeats().stream()
                .filter(f->f.getId().equals(event.getCarPlaceId()))
                .findFirst()
                .ifPresent(place -> place.setTicketEntity(null));
        eventDao.deleteByCarPlaceId(event.getCarPlaceId());
        zoneDao.update(zone);
    }
}
