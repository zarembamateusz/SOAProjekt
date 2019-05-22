package entity;

import lombok.Builder;
import lombok.Value;
import models.ZoneEntity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class UserEntity {
    @Id
    @Builder.Default
    private final String id = UUID.randomUUID().toString();
    private final String firstName;
    private final String lastName;
    private final String login;
    private final String password;
    @Enumerated(EnumType.STRING)
    private final Role role;
    @ManyToMany
    private final Set<ZoneEntity> zones;

}
