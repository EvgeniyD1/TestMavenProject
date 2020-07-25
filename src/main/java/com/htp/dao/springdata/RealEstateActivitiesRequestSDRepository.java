package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateRealEstateActivitiesRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RealEstateActivitiesRequestSDRepository extends
        CrudRepository<HibernateRealEstateActivitiesRequest, Long>,
        JpaRepository<HibernateRealEstateActivitiesRequest, Long>,
        PagingAndSortingRepository<HibernateRealEstateActivitiesRequest, Long> {

    Optional<HibernateRealEstateActivitiesRequest> findById(Long activitiesId);

    @Query("select act from HibernateRealEstateActivitiesRequest act join act.activities activ where activ.id = :id")
    List<HibernateRealEstateActivitiesRequest> findAllRolesByUserId(@Param("id") Long activityId);

//    HibernateRealEstateActivitiesRequest save(HibernateRealEstateActivitiesRequest activitiesRequest);
//
//    void delete(HibernateRealEstateActivitiesRequest activitiesRequest);
}
