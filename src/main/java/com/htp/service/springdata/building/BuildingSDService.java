package com.htp.service.springdata.building;

import com.htp.domain.hibernate.HibernateBuilding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BuildingSDService {

    Page<HibernateBuilding> findAll(Pageable pageable);

    Optional<HibernateBuilding> findById(Long buildingId);

    List<HibernateBuilding> findByType(String type);

    HibernateBuilding save(HibernateBuilding building);

    void delete(HibernateBuilding building);
}
