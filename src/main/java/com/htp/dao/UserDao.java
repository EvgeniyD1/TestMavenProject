package com.htp.dao;

import com.htp.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAll();

    List<User> search(String searchParam);

    User findByLogin(String searchParam);

    Optional<User> findById(Long userId);

    User findOne(Long userId);

    User save(User user);

    User update(User user);

    int delete(User user);

    List<User> batchUpdate(List<User> users);
}