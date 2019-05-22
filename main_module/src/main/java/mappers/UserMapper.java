package mappers;


import entity.UserEntity;
import lombok.experimental.UtilityClass;
import models.User;
import models.ZoneEntity;
import utill.EntityUtill;

import java.util.Set;
import java.util.stream.Collectors;

import static utill.EntityUtill.extractId;

@UtilityClass
public class UserMapper {

    public User toDto(final UserEntity entity) {
        return User.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .id(entity.getId())
                .login(entity.getLogin())
                .password(entity.getPassword())
                .zones(entity.getZones().stream().map(ZoneEntity::getId).collect(Collectors.toSet()))
                .build();
    }


    public UserEntity toEntity(final User user, final Set<ZoneEntity> zones) {

        return UserEntity.builder()
                .id(extractId(user::getId))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .role(user.getRole())
                .password(user.getPassword())
                .zones(zones)
                .build();
    }
}
