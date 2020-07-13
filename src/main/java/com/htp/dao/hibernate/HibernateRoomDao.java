package com.htp.dao.hibernate;

import com.htp.domain.hibernate.HibernateRoom;

import java.util.List;
import java.util.Optional;

public interface HibernateRoomDao {

    List<HibernateRoom> findAll();

    List<HibernateRoom> findAllRoomsInBuilding(Long buildingId);

    HibernateRoom findOne(Long roomId);

    Optional<HibernateRoom> findById(Long roomId);

    HibernateRoom save(HibernateRoom room);

    HibernateRoom update(HibernateRoom room);

    long delete(HibernateRoom room);

    List<HibernateRoom> batchUpdate(List<HibernateRoom> rooms);
}
