package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarPlace implements Serializable {
    private String id;
    private Ticket currentTicket;

    public Boolean isTicketExpired() {
        if (currentTicket == null) return false;
        else return currentTicket.isExpired();
    }
    public boolean haveTicket() {
        return currentTicket != null;
    }
}
