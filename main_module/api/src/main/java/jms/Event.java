package jms;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime timeOfCreated = LocalDateTime.now();

}
