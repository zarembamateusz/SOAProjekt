package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
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
    @OneToOne
    @JoinTable(name = "place_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CarPlaceEntity carPlace;
}
