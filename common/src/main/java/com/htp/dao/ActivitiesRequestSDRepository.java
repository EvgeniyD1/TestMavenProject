package com.htp.dao;

import com.htp.domain.HibernateActivitiesRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActivitiesRequestSDRepository extends
        CrudRepository<HibernateActivitiesRequest, Long>,
        JpaRepository<HibernateActivitiesRequest, Long>,
        PagingAndSortingRepository<HibernateActivitiesRequest, Long> {

    Optional<HibernateActivitiesRequest> findById(Long activitiesId);

    @Query("select act from HibernateActivitiesRequest act join act.activities activ where activ.id = :id")
    List<HibernateActivitiesRequest> findAllActivitiesRequestByActivitiesId(@Param("id") Long activityId);

}
