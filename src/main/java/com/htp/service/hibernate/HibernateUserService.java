package com.htp.service.hibernate;

import com.htp.domain.hibernate.HibernateUser;

import java.util.List;
import java.util.Optional;

public interface HibernateUserService {

    List<HibernateUser> findAll();

    List<HibernateUser> searchUserByFullName(String username, String surname, String patronymic);

    HibernateUser findByLogin(String login);

    HibernateUser findOne(Long userId);

    Optional<HibernateUser> findById(Long userId);

    HibernateUser save(HibernateUser user);

    HibernateUser update(HibernateUser user);

    void delete(HibernateUser user);

    List<HibernateUser> batchUpdate(List<HibernateUser> users);
}
