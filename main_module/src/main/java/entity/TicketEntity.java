package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {
    @Id
    @Builder.Default
    private final String id = UUID.randomUUID().toString();
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
