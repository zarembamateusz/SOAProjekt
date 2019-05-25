package models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
public class Ticket implements Serializable {
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public boolean isExpired() {
        return endTime.isBefore(LocalDateTime.now());
    }
}
