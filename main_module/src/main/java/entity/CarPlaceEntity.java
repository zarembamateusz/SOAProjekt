package entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Value
@Builder
public class CarPlaceEntity {
    @Id
    @Builder.Default
    private final String id = UUID.randomUUID().toString();
    @OneToOne
    private final TicketEntity ticketEntity;
}
