package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateActivities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivitiesSDRepository extends CrudRepository<HibernateActivities, Long>,
        JpaRepository<HibernateActivities, Long>,
        PagingAndSortingRepository<HibernateActivities, Long> {

    Page<HibernateActivities> findAll(Pageable pageable);

    Optional<HibernateActivities> findById(Long activitiesId);

    List<HibernateActivities> findByType(String type);

//    HibernateRealEstateActivities save(HibernateRealEstateActivities activities);

//    void delete(HibernateRealEstateActivities activities);
}
