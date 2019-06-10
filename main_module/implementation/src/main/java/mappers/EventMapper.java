package mappers;

import entity.EventEntity;
import entity.UserEntity;
import jms.Event;
import lombok.experimental.UtilityClass;
import utill.EntityUtill;

@UtilityClass
public class EventMapper {
    public Event toDto(final EventEntity eventEntity) {
        return Event.builder()
                .carPlaceId(eventEntity.getCarPlaceId())
                .description(eventEntity.getDescription())
                .type(eventEntity.getType())
                .zoneId(eventEntity.getZoneId())
                .id(eventEntity.getId())
                .timeOfCreated(eventEntity.getTimeOfCreated())
                .build();
    }

    public EventEntity toEntity(final Event event, String userId) {
        return EventEntity
                .builder()
                .id(EntityUtill.extractId(event::getId))
                .description(event.getDescription())
                .carPlaceId(event.getCarPlaceId())
                .zoneId(event.getZoneId())
                .timeOfCreated(event.getTimeOfCreated())
                .userId(userId)
                .type(event.getType())
                .build();
    }
}
