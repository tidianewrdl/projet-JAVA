package com.example.usermanagement.dao;

import com.example.usermanagement.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean save(User user);
    boolean update(User user);
    boolean delete(int id);
    Optional<User> findById(int id);
    Optional<User> findByLogin(String login);
    List<User> findAll();
    List<User> search(String query);
}
