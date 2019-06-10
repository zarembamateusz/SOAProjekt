package jms;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private LocalDateTime timeOfCreated = LocalDateTime.now();

}
