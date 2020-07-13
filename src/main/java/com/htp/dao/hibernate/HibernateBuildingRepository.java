package com.htp.dao.hibernate;

import com.htp.domain.hibernate.HibernateBuilding;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository("hibernateBuildingRepository")
public class HibernateBuildingRepository implements HibernateBuildingDao {

    private final SessionFactory sessionFactory;

    public HibernateBuildingRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<HibernateBuilding> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select building from HibernateBuilding building " +
                    "order by building.id asc", HibernateBuilding.class).list();
        }
    }

    @Override
    public List<HibernateBuilding> searchType(String type) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<HibernateBuilding> query = session.createQuery(
                    "select building from HibernateBuilding building where building.type = :type",
                    HibernateBuilding.class);
            query.setParameter("type", type);
            return query.getResultList();
        }
    }

    @Override
    public List<HibernateBuilding> searchLocation(String country, String town, String street) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<HibernateBuilding> query;
            if (town.equals("0") && street.equals("0")) {
                query = session.createQuery("select building from HibernateBuilding building where " +
                                "building.countryLocation = :country",
                        HibernateBuilding.class);
                query.setParameter("country", country);
            } else if (street.equals("0")) {
                query = session.createQuery("select building from HibernateBuilding building where " +
                                "building.countryLocation = :country and building.townLocation = :town",
                        HibernateBuilding.class);
                query.setParameter("country", country);
                query.setParameter("town", town);
            } else {
                query = session.createQuery("select building from HibernateBuilding building where " +
                                "building.countryLocation = :country and building.townLocation = :town and " +
                                "building.streetLocation = :street",
                        HibernateBuilding.class);
                query.setParameter("country", country);
                query.setParameter("town", town);
                query.setParameter("street", street);
            }
            return query.getResultList();
        }
    }

    @Override
    public HibernateBuilding findOne(Long buildingId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateBuilding.class, buildingId);
        }
    }

    @Override
    public Optional<HibernateBuilding> findById(Long buildingId) {
        return Optional.of(findOne(buildingId));
    }

    @Override
    public HibernateBuilding save(HibernateBuilding building) {
        Session session = sessionFactory.getCurrentSession();
        session.save(building);
        return building;
    }

    @Override
    public HibernateBuilding update(HibernateBuilding building) {
        Session session = sessionFactory.getCurrentSession();
        session.update(building);
        return building;
    }

    @Override
    public long delete(HibernateBuilding building) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(building);
        return building.getId();
    }


    @Override
    public List<HibernateBuilding> batchUpdate(List<HibernateBuilding> buildings) {
        int count = 0;
        Session session = sessionFactory.getCurrentSession();
        for (HibernateBuilding building : buildings) {
            session.save(building);
            if (++count % 5 == 0) {
                session.flush();
                session.clear();
            }
        }
        return buildings;
    }
}
