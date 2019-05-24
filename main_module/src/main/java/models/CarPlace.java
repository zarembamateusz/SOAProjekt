package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarPlace {
    private String id;
    private Ticket currentTicket;

    public boolean isFree() {
        return currentTicket == null;
    }
}
