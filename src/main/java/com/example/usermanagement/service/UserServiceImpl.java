package com.example.usermanagement.service;

import com.example.usermanagement.dao.UserDao;
import com.example.usermanagement.dao.UserDaoImpl;
import com.example.usermanagement.model.User;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public boolean createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.delete(id);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public List<User> listUsers() {
        return userDao.findAll();
    }

    @Override
    public List<User> searchUsers(String query) {
        return userDao.search(query);
    }
}
