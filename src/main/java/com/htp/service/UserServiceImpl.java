package com.htp.service;

import com.htp.dao.UserDao;
import com.htp.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserDao userDao;

    public UserServiceImpl(@Qualifier("userRepositoryJdbcTemplate") UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> search(String searchParam) {
        return userDao.search(searchParam);
    }

    @Override
    public User findByLogin(String searchParam) {
        return userDao.findByLogin(searchParam);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public int delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public List<User> batchUpdate(List<User> users) {
        return userDao.batchUpdate(users);
    }
}
