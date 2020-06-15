package com.htp.service;

import com.htp.domain.Building;

import java.util.List;
import java.util.Optional;

public interface BuildingService {
    List<Building> findAll();

    List<Building> search(String searchParam);

    Optional<Building> findById(Long buildingId);

    Building findOne(Long buildingId);

    Building save(Building building);

    Building update(Building building);

    int delete(Building building);

    List<Building> batchUpdate(List<Building> buildings);
}
