package jms;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@Value
public class Event implements Serializable {
    private final String carPlaceId;
    private final EventType type;
    private final String description;
}
