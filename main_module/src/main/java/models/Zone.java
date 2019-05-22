package models;

import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Data
@Builder
public class Zone {
    private String id;
    private Set<CarPlace> places;
    private Set<String> workers;
}
