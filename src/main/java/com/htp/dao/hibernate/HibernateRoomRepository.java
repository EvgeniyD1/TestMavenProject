package com.htp.dao.hibernate;

import com.htp.domain.hibernate.HibernateBuilding;
import com.htp.domain.hibernate.HibernateRoom;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository("hibernateRoomRepository")
public class HibernateRoomRepository implements HibernateRoomDao{

    private final SessionFactory sessionFactory;

    public HibernateRoomRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<HibernateRoom> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select room from HibernateRoom room order by room.id asc",
                    HibernateRoom.class).list();
        }
    }

    @Override
    public List<HibernateRoom> findAllRoomsInBuilding(Long buildingId) {
        try (Session session = sessionFactory.openSession()) {
            HibernateBuildingDao dao = new HibernateBuildingRepository(sessionFactory);
            HibernateBuilding building = dao.findOne(buildingId);
            TypedQuery<HibernateRoom> query = session.createQuery(
                    "select room from HibernateRoom room where room.building = :buildingId",
                    HibernateRoom.class);
            query.setParameter("buildingId", building);
            return query.getResultList();
        }
    }

    @Override
    public HibernateRoom findOne(Long roomId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateRoom.class, roomId);
        }
    }

    @Override
    public Optional<HibernateRoom> findById(Long roomId) {
        return Optional.of(findOne(roomId));
    }

    @Override
    public HibernateRoom save(HibernateRoom room) {
        Session session = sessionFactory.getCurrentSession();
        session.save(room);
        return room;
    }

    @Override
    public HibernateRoom update(HibernateRoom room) {
        Session session = sessionFactory.getCurrentSession();
        session.update(room);
        return room;
    }

    @Override
    public long delete(HibernateRoom room) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(room);
        return room.getId();
    }

    @Override
    public List<HibernateRoom> batchUpdate(List<HibernateRoom> rooms) {
        int count = 0;
        Session session = sessionFactory.getCurrentSession();
        for (HibernateRoom room : rooms) {
            session.update(room);
            if (++count % 5 == 0) {
                session.flush();
                session.clear();
            }
        }
        return rooms;
    }
}
