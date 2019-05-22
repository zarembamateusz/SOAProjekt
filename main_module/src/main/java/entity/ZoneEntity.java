package models;

import entity.CarPlaceEntity;
import entity.UserEntity;
import lombok.Builder;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Value
@Builder
@Entity
public class ZoneEntity {
    private final String id;

    @OneToMany
    private final Set<CarPlaceEntity> seats;

    private final Set<UserEntity> responsibleUsers;

}
