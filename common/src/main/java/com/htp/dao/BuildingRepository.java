package com.htp.dao;

import com.htp.domain.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Long>,
        JpaRepository<Building, Long>,
        PagingAndSortingRepository<Building, Long>,
        JpaSpecificationExecutor<Building> {

    @Query("select building from Building building  where building.type = :type")
    List<Building> findByType(@Param("type") String type);

}
