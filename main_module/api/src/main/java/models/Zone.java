package models;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Zone implements Serializable {
    private String id;

    private String code;
    @Builder.Default
    @ToString.Exclude
    private Set<CarPlace> places = new HashSet<>();
    @Builder.Default
    @ToString.Exclude
    private Set<String> workers = new HashSet<>();
}
