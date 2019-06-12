package entity;

import jms.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
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
    private String carCode;
    private String zoneCode;
    @Builder.Default
    private LocalDateTime timeOfCreated = LocalDateTime.now();
}
