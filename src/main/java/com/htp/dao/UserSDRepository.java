package com.htp.dao;

import com.htp.domain.HibernateUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSDRepository extends CrudRepository<HibernateUser, Long>,
        JpaRepository<HibernateUser, Long>,
        PagingAndSortingRepository<HibernateUser, Long>,
        JpaSpecificationExecutor<HibernateUser> {

    Page<HibernateUser> findAll(Pageable pageable);

    Optional<HibernateUser> findById(Long userId);

    Optional<HibernateUser> findByLogin(String login);

}
