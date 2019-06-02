package mappers;

import entity.CarPlaceEntity;
import entity.TicketEntity;
import entity.UserEntity;
import entity.ZoneEntity;
import lombok.experimental.UtilityClass;
import lombok.val;
import models.CarPlace;
import models.Ticket;
import models.Zone;
import utill.EntityUtill;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class ZoneMapper {

    public ZoneEntity toEntity(final Zone dto, final Set<UserEntity> entities) {
        val zoneEntity = ZoneEntity.builder()
                .id(EntityUtill.extractId(dto::getId))
                .code(dto.getCode())
                .responsibleUsers(entities)
                .responsibleUsers(entities)
                .build();

        zoneEntity.setSeats(dto.getPlaces().stream().map(p -> toCarPlaceEntity(p, zoneEntity)).collect(Collectors.toSet()));
        return zoneEntity;
    }


    private CarPlaceEntity toCarPlaceEntity(final CarPlace place, final ZoneEntity zoneEntity) {
        return CarPlaceEntity.builder()
                .id(EntityUtill.extractId(place::getId))
                .ticketEntity(toTicketEntity(place.getCurrentTicket()))
                .zone(zoneEntity)
                .build();
    }

    private TicketEntity toTicketEntity(final Ticket ticket) {
        if (ticket == null) return null;
        else
            return TicketEntity.builder()
                    .id(EntityUtill.extractId(ticket::getId))
                    .endTime(ticket.getEndTime())
                    .startTime(ticket.getStartTime())
                    .build();
    }

    public Zone toDto(final ZoneEntity entity) {
        return Zone.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .workers(entity.getResponsibleUsers().stream().map(UserEntity::getId).collect(Collectors.toSet()))
                .places(entity.getSeats().stream().map(ZoneMapper::toCarPlace).collect(Collectors.toSet()))
                .build();
    }

    public CarPlace toCarPlace(final CarPlaceEntity entity) {

        return CarPlace.builder()
                .id(entity.getId())
                .currentTicket(toTicket(entity.getTicketEntity()))
                .build();
    }

    private Ticket toTicket(final TicketEntity entity) {
        return Optional.ofNullable(entity)
                .map(en -> Ticket.builder()
                        .endTime(en.getEndTime())
                        .startTime(en.getStartTime())
                        .build()).orElse(null);

    }
}
