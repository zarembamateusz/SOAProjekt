package models.service;

import models.User;
import models.Zone;

import java.time.LocalDateTime;
import java.util.List;

public interface ZoneService extends GenericService<Zone, String> {

    void reserve(final String zoneId, final String placeId, LocalDateTime endTime);
    void create(final String code, final int number, final List<User> responsibleUsers);

    Zone getZoneByCode(String code);
}
