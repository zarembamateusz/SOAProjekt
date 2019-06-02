package jms;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event implements Serializable {
    private String id;
    private String zoneId;
    private String carPlaceId;
    private EventType type;
    private String description;
    private LocalDateTime timeOfCreated = LocalDateTime.now();

}
