package com.htp.dao;

import com.htp.domain.HibernateBuilding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingSDRepository extends CrudRepository<HibernateBuilding, Long>,
        JpaRepository<HibernateBuilding, Long>,
        PagingAndSortingRepository<HibernateBuilding, Long>,
        JpaSpecificationExecutor<HibernateBuilding> {

    Page<HibernateBuilding> findAll(Pageable pageable);

    Optional<HibernateBuilding> findById(Long buildingId);

    @Query("select building from HibernateBuilding building  where building.type = :type")
    List<HibernateBuilding> findByType(@Param("type") String type);

}
