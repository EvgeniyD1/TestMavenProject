package com.htp.dao;

import com.htp.domain.ActivitiesRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivitiesRequestRepository extends
        CrudRepository<ActivitiesRequest, Long>,
        JpaRepository<ActivitiesRequest, Long>,
        PagingAndSortingRepository<ActivitiesRequest, Long> {

    @Query("select act from ActivitiesRequest act join act.activities activ where activ.id = :id")
    List<ActivitiesRequest> findAllActivitiesRequestByActivitiesId(@Param("id") Long activityId);

}
