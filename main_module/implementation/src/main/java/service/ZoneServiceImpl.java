package service;

import dao.EventDao;
import dao.UserDao;
import dao.ZoneDao;
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
public class ZoneServiceImpl implements ZoneService, Serializable {

    private final UserDao userDao = UserDao.create();
    private final ZoneDao zoneDao = ZoneDao.create();

    @Override
    public void update(Zone zone) {
        zoneDao.update(toEntity(zone));
    }

    @Override
    public void create(Zone zone) {
        zoneDao.create(toEntity(zone));

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
        return ZoneMapper.toEntity(zone, workers);
    }


    private Set<CarPlace> carPlaces(final int number) {
        return Stream.iterate(0, i -> i + 1).limit(number)
                .map(i -> CarPlace.builder()
                        .id(UUID.randomUUID()
                                .toString())
                        .build()
                ).collect(Collectors.toSet());
    }
}
