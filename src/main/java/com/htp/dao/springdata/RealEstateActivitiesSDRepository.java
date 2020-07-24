package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateRealEstateActivities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealEstateActivitiesSDRepository extends CrudRepository<HibernateRealEstateActivities, Long>,
        JpaRepository<HibernateRealEstateActivities, Long>,
        PagingAndSortingRepository<HibernateRealEstateActivities, Long> {
}
