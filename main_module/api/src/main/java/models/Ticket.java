package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public boolean checkIfIsExpired() {
        return endTime.isBefore(LocalDateTime.now());
    }
}
