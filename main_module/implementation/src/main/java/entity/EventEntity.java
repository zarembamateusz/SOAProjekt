package entity;

import jms.EventType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import models.CarPlace;
import models.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@Data
public class EventEntity {
    @Id
    private String id;
    private EventType type;
    private String  carPlaceId;
    private String description;
    private String userId;
    private String zoneId;
    @Builder.Default
    private LocalDateTime timeOfCreated = LocalDateTime.now();
}
