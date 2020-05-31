package com.htp.dao;

import com.htp.domain.Building;

import java.util.List;
import java.util.Optional;

public interface BuildingDao {
    List<Building> findAll();

    List<Building> search(String searchParam);

    Optional<Building> findById(Long buildingId);

    Building findOne(Long buildingId);

    Building save(Building building);

    Building update(Building building);

    int delete(Building building);

    List<Building> batch(Building building);
}
