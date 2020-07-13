package com.htp.dao.hibernate;

import com.htp.domain.hibernate.HibernateUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository("hibernateUserRepository")
public class HibernateUserRepository implements HibernateUserDao {

    private final SessionFactory sessionFactory;

    public HibernateUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<HibernateUser> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select user from HibernateUser user order by user.id asc",
                    HibernateUser.class).list();
        }
    }

    @Override
    public List<HibernateUser> searchUserByFullName(String username, String surname, String patronymic) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<HibernateUser> query;
            if (patronymic.equals("0") && username.equals("0")) {
                query = session.createQuery(
                        "select user from HibernateUser user where user.surname = :surname",
                        HibernateUser.class);
                query.setParameter("surname", surname);
            } else if (patronymic.equals("0")) {
                query = session.createQuery(
                        "select user from HibernateUser user where user.surname = :surname " +
                                "and user.username = :username",
                        HibernateUser.class);
                query.setParameter("username", username);
                query.setParameter("surname", surname);
            } else {
                query = session.createQuery(
                        "select user from HibernateUser user where user.username = :username " +
                                "and user.surname = :surname and user.patronymic = :patronymic",
                        HibernateUser.class);
                query.setParameter("username", username);
                query.setParameter("surname", surname);
                query.setParameter("patronymic", patronymic);
            }
            return query.getResultList();
        }
    }

    @Override
    public HibernateUser findByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<HibernateUser> query = session.createQuery(
                    "select user from HibernateUser user where user.login = :login", HibernateUser.class);
            query.setParameter("login", login);
            return query.getSingleResult();
        }
    }

    @Override
    public HibernateUser findOne(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateUser.class, userId);
        }
    }

    @Override
    public Optional<HibernateUser> findById(Long userId) {
        return Optional.of(findOne(userId));
    }

    @Override
    public HibernateUser save(HibernateUser user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return user;
    }

    @Override
    public HibernateUser update(HibernateUser user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        return user;
    }

    @Override
    public void delete(HibernateUser user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public List<HibernateUser> batchUpdate(List<HibernateUser> users) {
        int count = 0;
        Session session = sessionFactory.getCurrentSession();
        for (HibernateUser user : users) {
            session.update(user);
            if (++count % 5 == 0) {
                session.flush();
                session.clear();
            }
        }
        return users;
    }
}
