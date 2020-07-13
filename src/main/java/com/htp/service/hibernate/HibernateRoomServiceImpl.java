package com.htp.service.hibernate;

import com.htp.dao.hibernate.HibernateRoomDao;
import com.htp.domain.hibernate.HibernateRoom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HibernateRoomServiceImpl implements HibernateRoomService{

    private final HibernateRoomDao hibernateRoomDao;

    public HibernateRoomServiceImpl(HibernateRoomDao hibernateRoomDao) {
        this.hibernateRoomDao = hibernateRoomDao;
    }

    @Override
    public List<HibernateRoom> findAll() {
        return hibernateRoomDao.findAll();
    }

    @Override
    public List<HibernateRoom> findAllRoomsInBuilding(Long buildingId) {
        return hibernateRoomDao.findAllRoomsInBuilding(buildingId);
    }

    @Override
    public HibernateRoom findOne(Long roomId) {
        return hibernateRoomDao.findOne(roomId);
    }

    @Override
    public Optional<HibernateRoom> findById(Long roomId) {
        return hibernateRoomDao.findById(roomId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateRoom save(HibernateRoom room) {
        return hibernateRoomDao.save(room);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateRoom update(HibernateRoom room) {
        return hibernateRoomDao.update(room);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public long delete(HibernateRoom room) {
        return hibernateRoomDao.delete(room);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public List<HibernateRoom> batchUpdate(List<HibernateRoom> rooms) {
        return hibernateRoomDao.batchUpdate(rooms);
    }
}
