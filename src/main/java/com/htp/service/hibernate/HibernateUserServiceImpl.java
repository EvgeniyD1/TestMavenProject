package com.htp.service.hibernate;

import com.htp.dao.hibernate.HibernateUserDao;
import com.htp.domain.hibernate.HibernateUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HibernateUserServiceImpl implements HibernateUserService {

    private final HibernateUserDao hibernateUserDao;

    public HibernateUserServiceImpl(HibernateUserDao hibernateUserDao) {
        this.hibernateUserDao = hibernateUserDao;
    }

    @Override
    public List<HibernateUser> findAll() {
        return  hibernateUserDao.findAll();
    }

    @Override
    public List<HibernateUser> searchUserByFullName(String username, String surname, String patronymic) {
        return hibernateUserDao.searchUserByFullName(username, surname, patronymic);
    }

    @Override
    public HibernateUser findByLogin(String login) {
        return hibernateUserDao.findByLogin(login);
    }

    @Override
    public HibernateUser findOne(Long userId) {
        return hibernateUserDao.findOne(userId);
    }

    @Override
    public Optional<HibernateUser> findById(Long userId) {
        return hibernateUserDao.findById(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateUser save(HibernateUser user) {
        return hibernateUserDao.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateUser update(HibernateUser user) {
        return hibernateUserDao.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(HibernateUser user) {
        hibernateUserDao.delete(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public List<HibernateUser> batchUpdate(List<HibernateUser> users) {
        return hibernateUserDao.batchUpdate(users);
    }
}
