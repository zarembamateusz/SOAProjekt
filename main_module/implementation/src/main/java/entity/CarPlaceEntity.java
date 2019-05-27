package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @OneToOne(cascade = CascadeType.ALL)
    private TicketEntity ticketEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    private ZoneEntity zone;
}
