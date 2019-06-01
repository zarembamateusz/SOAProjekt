package jms;


import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event implements Serializable {
    private String carPlaceId;
    private EventType type;
    private String description;
}
