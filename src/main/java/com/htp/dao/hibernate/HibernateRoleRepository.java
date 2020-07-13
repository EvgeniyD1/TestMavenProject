package com.htp.dao.hibernate;

import com.htp.domain.hibernate.HibernateRole;
import com.htp.domain.hibernate.HibernateUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository("hibernateRoleRepository")
public class HibernateRoleRepository implements HibernateRoleDao {

    private final SessionFactory sessionFactory;

    public HibernateRoleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<HibernateRole> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select role from HibernateRole role order by role.id asc",
                    HibernateRole.class).list();
        }
    }

    @Override
    public HibernateRole findOne(Long roleId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateRole.class, roleId);
        }
    }

    @Override
    public Optional<HibernateRole> findById(Long roleId) {
        return Optional.of(findOne(roleId));
    }

    @Override
    public List<HibernateRole> findAllUserRoles(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            HibernateUserRepository hibernateUserRepository = new HibernateUserRepository(sessionFactory);
            HibernateUser user = hibernateUserRepository.findOne(userId);
            TypedQuery<HibernateRole> query = session.createQuery(
                    "select role from HibernateRole role where role.user = :userId", HibernateRole.class);
            query.setParameter("userId", user);
            return query.getResultList();
        }
    }

    @Override
    public HibernateRole save(HibernateRole role) {
        Session session = sessionFactory.getCurrentSession();
        session.save(role);
        return role;
    }

    @Override
    public HibernateRole update(HibernateRole role) {
        Session session = sessionFactory.getCurrentSession();
        session.update(role);
        return role;
    }

    @Override
    public void delete(HibernateRole role) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(role);
    }

    @Override
    public List<HibernateRole> batchInsert(List<HibernateRole> roles) {
        int count = 0;
        Session session = sessionFactory.getCurrentSession();
        for (HibernateRole role : roles) {
            session.save(role);
            if (++count % 5 == 0) {
                session.flush();
                session.clear();
            }
        }
        return roles;
    }
}
