package models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
public class Zone implements Serializable {
    private String id;
    @Builder.Default
    private Set<CarPlace> places = new HashSet<>();
    @Builder.Default
    private Set<String> workers = new HashSet<>();
}
