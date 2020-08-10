package com.htp.service.building;

import com.htp.domain.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface BuildingService {

    Page<Building> findAll(Pageable pageable);

    List<Building> criteriaSpecification(Specification<Building> spec);

    Optional<Building> findById(Long buildingId);

    List<Building> findByType(String type);

    Building save(Building building);

    void delete(Building building);
}
