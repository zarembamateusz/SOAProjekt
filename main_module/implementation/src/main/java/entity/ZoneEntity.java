package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ZoneEntity {

    @Id
    @Builder.Default
    private final String id = UUID.randomUUID().toString();

    @OneToMany(mappedBy = "zone")
    private  Set<CarPlaceEntity> seats;

    @ManyToMany
    @JoinTable(
            name = "work_zone",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "zone_id"))
    private  Set<UserEntity> responsibleUsers;

}
