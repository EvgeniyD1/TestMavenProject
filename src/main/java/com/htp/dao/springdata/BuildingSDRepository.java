package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingSDRepository extends CrudRepository<HibernateBuilding, Long>,
        JpaRepository<HibernateBuilding, Long>,
        PagingAndSortingRepository<HibernateBuilding, Long> {

    @Query("select building from HibernateBuilding building  where building.type = :type")
    List<HibernateBuilding> findByType(@Param("type") String type);
}
