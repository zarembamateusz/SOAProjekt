package service;

import dao.EventDao;
import dao.UserDao;
import dao.ZoneDao;
import entity.UserEntity;
import jms.Event;
import models.service.EventService;
import lombok.val;
import mappers.EventMapper;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class EventServiceImpl implements EventService {

    private final ZoneDao zoneDao = ZoneDao.create();
    private EventDao eventDao = EventDao.create();
    private final UserDao userDao = UserDao.create();

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
