package service;


import dao.UserDao;
import dao.ZoneDao;
import entity.UserEntity;
import entity.ZoneEntity;
import lombok.val;
import mappers.UserMapper;
import models.Role;
import models.User;
import models.service.UserService;

import javax.annotation.security.PermitAll;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static models.Role.MANAGER;
import static models.Role.WORKER;


@Remote(UserService.class)
@Stateless

public class UserServiceImpl implements UserService {

    private final UserDao userDao = UserDao.create();
    private final ZoneDao zoneDao = ZoneDao.create();


    public UserServiceImpl() {
    }

    @Override
    public void update(User user) {
        userDao.update(toEntity(user));
    }

    @Override
    public void create(User user) {
        userDao.create(toEntity(user));
    }

    @Override
    public void delete(User user) {
        userDao.delete(toEntity(user));

    }

    @Override
    public void deleteById(String string) {
        userDao.deleteById(string);

    }

    @Override
    public User findById(String id) {
        return UserMapper.toDto(userDao.findById(id));
    }

    @PermitAll
    @Override
    public List<User> getAll() {
        return userDao.getAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    private UserEntity toEntity(final User user) {
        Set<ZoneEntity> zones;
        if (MANAGER.equals(user.getRole())) zones = new HashSet<>(zoneDao.getAll());
        else {
            zones = user.getZones().stream()
                    .filter(Objects::nonNull)
                    .map(zoneDao::findById)
                    .collect(Collectors.toSet());
        }
        val entity = UserMapper.toEntity(user, zones);
        zones.forEach(zoneEntity -> {
            val users = zoneEntity.getResponsibleUsers();
            users.add(entity);
        });
        return entity;
    }

    @Override
    public List<User> getByZoneId(String zoneId) {
        return userDao.findByZoneId(zoneId).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAdmins() {
        return getAll()
                .stream()
                .filter(user -> user.getRole().equals(MANAGER))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getWorkers() {
        return getAll()
                .stream()
                .filter(user -> user.getRole().equals(WORKER))
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(final String login, final String password, final String name,
                           final String lastName, final Role role, final String zoneId) {

        val user = User.builder()
                .zone(zoneId)
                .password(password)
                .login(login)
                .lastName(lastName)
                .firstName(name)
                .role(role)
                .build();
        create(user);
    }
}
