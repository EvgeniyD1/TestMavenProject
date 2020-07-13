package com.htp.dao.hibernate;

import com.htp.domain.hibernate.HibernateBuilding;

import java.util.List;
import java.util.Optional;

public interface HibernateBuildingDao {

    List<HibernateBuilding> findAll();

    List<HibernateBuilding> searchType(String type);

    List<HibernateBuilding> searchLocation(String country, String town, String street);

    HibernateBuilding findOne(Long buildingId);

    Optional<HibernateBuilding> findById(Long buildingId);

    HibernateBuilding save(HibernateBuilding building);

    HibernateBuilding update(HibernateBuilding building);

    long delete(HibernateBuilding building);

    List<HibernateBuilding> batchUpdate(List<HibernateBuilding> buildings);
}
