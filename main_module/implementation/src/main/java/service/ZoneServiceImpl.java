package service;

import dao.EventDao;
import dao.UserDao;
import dao.ZoneDao;
import entity.UserEntity;
import entity.ZoneEntity;
import jms.Event;
import lombok.NoArgsConstructor;
import lombok.val;
import mappers.ZoneMapper;
import models.CarPlace;
import models.Ticket;
import models.User;
import models.Zone;
import models.service.ZoneService;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.security.PermitAll;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Remote(ZoneService.class)
@Stateless
@NoArgsConstructor
@PermitAll
@SecurityDomain("test-policy")
public class ZoneServiceImpl implements ZoneService, Serializable {

    private final UserDao userDao = UserDao.create();
    private final ZoneDao zoneDao = ZoneDao.create();
    private final EventDao eventDao = EventDao.create();

    @Override
    public void update(Zone zone) {
        zoneDao.update(toEntity(zone));
    }

    @Override
    public void create(Zone zone) {
        val workers = zone.getWorkers()
                .stream()
                .map(userDao::findById)
                .collect(Collectors.toSet());
        val entity = ZoneMapper.toEntity(zone,workers);
        workers.stream().map(UserEntity::getZones).forEach(zones-> zones.add(entity));

        zoneDao.create(entity);

    }

    @Override
    public void create(final String code, final int number, final List<User> responsibleUsers) {
        val zone = Zone.builder()
                .code(code)
                .workers(responsibleUsers.stream().map(User::getId).collect(Collectors.toSet()))
                .places(carPlaces(number))
                .build();
        zoneDao.create(toEntity(zone));

    }

    @Override
    public Zone getZoneByCode(String code) {
        return getAll()
                .stream()
                .filter(zone -> zone.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }



    @Override
    public void delete(Zone zone) {
        zoneDao.delete(toEntity(zone));

    }

    @Override
    public void deleteById(String id) {
        zoneDao.deleteById(id);
    }

    @Override
    public Zone findById(String id) {
        return ZoneMapper.toDto(zoneDao.findById(id));
    }

    @Override
    public List<Zone> getAll() {
        return zoneDao.getAll().stream().map(ZoneMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void reserve(String zoneId, String placeId, LocalDateTime endTime) {
        val zone = findById(zoneId);
        zone.getPlaces().stream()
                .filter(place -> place.getId().equals(placeId))
                .findFirst()
                .ifPresent(f -> f.setCurrentTicket(Ticket.builder()
                        .startTime(LocalDateTime.now())
                        .endTime(endTime)
                        .build()));
        eventDao.findAllPlaceEvents(placeId)
                .forEach(eventDao::delete);
        update(zone);
    }

    public CarPlace getByCarId(String id) {
        return ZoneMapper.toCarPlace(zoneDao.getCarPlaceById(id));
    }

    private ZoneEntity toEntity(final Zone zone) {
        val workers = zone.getWorkers()
                .stream()
                .map(userDao::findById)
                .collect(Collectors.toSet());
        val entity = ZoneMapper.toEntity(zone, workers);
        return entity;
    }


    private Set<CarPlace> carPlaces(final int number) {
        return Stream.iterate(0, i -> i + 1).limit(number)
                .map(i -> CarPlace.builder()
                        .id(UUID.randomUUID()
                                .toString())
                        .code(i.toString())
                        .build()
                ).collect(Collectors.toSet());
    }
}
