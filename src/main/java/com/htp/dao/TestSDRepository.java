package com.htp.dao;

import com.htp.domain.HibernateUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestSDRepository extends CrudRepository<HibernateUser, Long>,
        JpaRepository<HibernateUser, Long>,
        PagingAndSortingRepository<HibernateUser, Long> {

    @Query("select user from HibernateUser user where user.blocked = false")
    List<HibernateUser> findByBlocked(Pageable pageable);

    @Query("select user from HibernateUser user where user.blocked = false")
    Page<HibernateUser> findByBlocked2(Pageable pageable);

}
