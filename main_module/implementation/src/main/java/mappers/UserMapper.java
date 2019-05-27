package mappers;


import entity.UserEntity;
import entity.ZoneEntity;
import lombok.experimental.UtilityClass;
import models.User;
import utill.EntityUtill;

import java.util.Set;
import java.util.stream.Collectors;


@UtilityClass
public class UserMapper {

    public User toDto(final UserEntity entity) {
        return User.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .id(entity.getId())
                .login(entity.getLogin())
                .password(entity.getPassword())
                .role(entity.getRole())
                .zones(entity.getZones().stream().map(ZoneEntity::getId).collect(Collectors.toSet()))
                .build();
    }


    public UserEntity toEntity(final User user, final Set<ZoneEntity> zones) {

        return UserEntity.builder()
                .id(EntityUtill.extractId(user::getId))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .role(user.getRole())
                .password(user.getPassword())
                .zones(zones)
                .build();
    }
}
