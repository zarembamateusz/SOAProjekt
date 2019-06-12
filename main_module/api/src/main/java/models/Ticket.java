package models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String startTime;
    private String endTime;

    public boolean checkIfIsExpired() {
        return LocalDateTime.parse(endTime).isBefore(LocalDateTime.now());
    }
}
