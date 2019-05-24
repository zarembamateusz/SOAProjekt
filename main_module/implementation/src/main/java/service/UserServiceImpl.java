package service;


import dao.UserDao;
import dao.ZoneDao;
import entity.UserEntity;
import lombok.val;
import mappers.UserMapper;
import models.User;
import models.service.UserService;

import javax.ejb.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


@Remote(UserService.class)
@Stateless
public class UserServiceImpl implements UserService {

    private final UserDao userDao = UserDao.create();
    private final ZoneDao zoneDao = ZoneDao.create();


    public UserServiceImpl(){}

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

    @Override
    public List<User> getAll() {
        return userDao.getAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    private UserEntity toEntity(final User user) {
        val zones = user.getZones().stream()
                .map(zoneDao::findById)
                .collect(Collectors.toSet());
        return UserMapper.toEntity(user, zones);
    }
}
