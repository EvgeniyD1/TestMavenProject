package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleSDRepository extends CrudRepository<HibernateSale, Long>,
        JpaRepository<HibernateSale, Long>,
        PagingAndSortingRepository<HibernateSale, Long> {
}
