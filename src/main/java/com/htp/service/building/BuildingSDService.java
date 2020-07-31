package com.htp.service.building;

import com.htp.domain.HibernateBuilding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface BuildingSDService {

    Page<HibernateBuilding> findAll(Pageable pageable);

    List<HibernateBuilding> criteriaSpecification(Specification<HibernateBuilding> spec);

    Optional<HibernateBuilding> findById(Long buildingId);

    List<HibernateBuilding> findByType(String type);

    HibernateBuilding save(HibernateBuilding building);

    void delete(HibernateBuilding building);
}
