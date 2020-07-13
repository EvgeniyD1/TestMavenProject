package com.htp.service.hibernate;

import com.htp.dao.hibernate.HibernateBuildingDao;
import com.htp.domain.hibernate.HibernateBuilding;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HibernateBuildingServiceImpl implements HibernateBuildingService{

    private final HibernateBuildingDao hibernateBuildingDao;

    public HibernateBuildingServiceImpl(HibernateBuildingDao hibernateBuildingDao) {
        this.hibernateBuildingDao = hibernateBuildingDao;
    }

    @Override
    public List<HibernateBuilding> findAll() {
        return hibernateBuildingDao.findAll();
    }

    @Override
    public List<HibernateBuilding> searchType(String type) {
        return hibernateBuildingDao.searchType(type);
    }

    @Override
    public List<HibernateBuilding> searchLocation(String country, String town, String street) {
        return hibernateBuildingDao.searchLocation(country, town, street);
    }

    @Override
    public HibernateBuilding findOne(Long buildingId) {
        return hibernateBuildingDao.findOne(buildingId);
    }

    @Override
    public Optional<HibernateBuilding> findById(Long buildingId) {
        return hibernateBuildingDao.findById(buildingId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateBuilding save(HibernateBuilding building) {
        return hibernateBuildingDao.save(building);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateBuilding update(HibernateBuilding building) {
        return hibernateBuildingDao.update(building);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public long delete(HibernateBuilding building) {
        return hibernateBuildingDao.delete(building);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public List<HibernateBuilding> batchUpdate(List<HibernateBuilding> buildings) {
        return hibernateBuildingDao.batchUpdate(buildings);
    }
}
