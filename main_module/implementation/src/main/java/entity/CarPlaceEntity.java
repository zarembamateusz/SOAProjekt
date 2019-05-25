package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CarPlaceEntity {
    @Id
    @Builder.Default
    private final String id = UUID.randomUUID().toString();
    @OneToOne
    private TicketEntity ticketEntity;

    @ManyToOne
    private ZoneEntity zone;
}
