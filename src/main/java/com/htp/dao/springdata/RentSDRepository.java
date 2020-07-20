package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentSDRepository extends CrudRepository<HibernateRent, Long>,
        JpaRepository<HibernateRent, Long>,
        PagingAndSortingRepository<HibernateRent, Long> {

}
