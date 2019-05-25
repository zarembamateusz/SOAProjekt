package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Role;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Builder.Default
    private final String id = UUID.randomUUID().toString();
    private  String firstName;
    private  String lastName;
    private  String login;
    private  String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(mappedBy = "responsibleUsers")
    private  Set<ZoneEntity> zones;

}
