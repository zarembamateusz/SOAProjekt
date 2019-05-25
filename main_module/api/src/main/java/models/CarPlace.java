package models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CarPlace implements Serializable {
    private String id;
    private Ticket currentTicket;

    public boolean isFree() {
        return currentTicket == null;
    }
}
