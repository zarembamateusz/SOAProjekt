package service;

import dao.UserDao;
import dao.ZoneDao;
import entity.ZoneEntity;
import lombok.NoArgsConstructor;
import lombok.val;
import mappers.ZoneMapper;
import models.Zone;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(staticName = "create")
public class ZoneService implements GenericService<Zone, String> {

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

    private ZoneEntity toEntity(final Zone zone) {
        val workers = zone.getWorkers()
                .stream()
                .map(userDao::findById)
                .collect(Collectors.toSet());
        return ZoneMapper.toEntity(zone, workers);
    }
}
