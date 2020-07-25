package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateRealEstateActivities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RealEstateActivitiesSDRepository extends CrudRepository<HibernateRealEstateActivities, Long>,
        JpaRepository<HibernateRealEstateActivities, Long>,
        PagingAndSortingRepository<HibernateRealEstateActivities, Long> {

    Page<HibernateRealEstateActivities> findAll(Pageable pageable);

    Optional<HibernateRealEstateActivities> findById(Long activitiesId);

    List<HibernateRealEstateActivities> findByType(String type);

//    HibernateRealEstateActivities save(HibernateRealEstateActivities activities);

//    void delete(HibernateRealEstateActivities activities);
}
