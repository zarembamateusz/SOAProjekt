package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int free;
    private String code;

    @JsonIgnore
    public Boolean isTicketExpired() {
        if (currentTicket == null) return false;
        else return currentTicket.checkIfIsExpired();
    }
    public boolean haveTicket() {
        return currentTicket != null;
    }
}
