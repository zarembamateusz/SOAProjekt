package entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class TicketEntity {
    @Id
    @Builder.Default
    private final String id = UUID.randomUUID().toString();
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
}
