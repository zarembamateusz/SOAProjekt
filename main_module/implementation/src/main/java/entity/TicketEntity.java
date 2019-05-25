package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
