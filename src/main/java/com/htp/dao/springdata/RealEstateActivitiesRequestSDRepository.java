package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateRealEstateActivitiesRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RealEstateActivitiesRequestSDRepository extends
        CrudRepository<HibernateRealEstateActivitiesRequest, Long>,
        JpaRepository<HibernateRealEstateActivitiesRequest, Long>,
        PagingAndSortingRepository<HibernateRealEstateActivitiesRequest, Long> {
}
