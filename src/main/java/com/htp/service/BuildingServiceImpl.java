package com.htp.service;

import com.htp.dao.BuildingDao;
import com.htp.domain.Building;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingServiceImpl implements BuildingService{

    private BuildingDao buildingDao;

    public BuildingServiceImpl(@Qualifier("buildingRepositoryJdbcTemplate") BuildingDao buildingDao) {
        this.buildingDao = buildingDao;
    }

    @Override
    public List<Building> findAll() {
        return buildingDao.findAll();
    }

    @Override
    public List<Building> search(String searchParam) {
        return buildingDao.search(searchParam);
    }

    @Override
    public Optional<Building> findById(Long buildingId) {
        return buildingDao.findById(buildingId);
    }

    @Override
    public Building findOne(Long buildingId) {
        return buildingDao.findOne(buildingId);
    }

    @Override
    public Building save(Building building) {
        return buildingDao.save(building);
    }

    @Override
    public Building update(Building building) {
        return buildingDao.update(building);
    }

    @Override
    public int delete(Building building) {
        return buildingDao.delete(building);
    }

    @Override
    public List<Building> batchUpdate(List<Building> buildings) {
        return buildingDao.batchUpdate(buildings);
    }
}
