package Models;

import lombok.Value;

@Value
public class CarPlace {
    private final String id;
    private final Boolean isFree;
    private final Ticket ticket;
}
