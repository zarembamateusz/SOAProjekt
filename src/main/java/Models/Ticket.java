package Models;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Ticket {
    private final String id;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
}
