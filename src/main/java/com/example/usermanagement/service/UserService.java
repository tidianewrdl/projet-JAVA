package com.example.usermanagement.service;

import com.example.usermanagement.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean createUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    Optional<User> getUserById(int id);
    Optional<User> getUserByLogin(String login);
    List<User> listUsers();
    List<User> searchUsers(String query);
}
