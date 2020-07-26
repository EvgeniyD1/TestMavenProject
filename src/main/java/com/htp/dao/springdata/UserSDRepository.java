package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSDRepository extends CrudRepository<HibernateUser, Long>,
        JpaRepository<HibernateUser, Long>,
        PagingAndSortingRepository<HibernateUser, Long> {

    Page<HibernateUser> findAll(Pageable pageable);

    Optional<HibernateUser> findById(Long userId);

    Optional<HibernateUser> findByLogin(String login);

//    HibernateUser save(HibernateUser user);
//
//    void delete(HibernateUser user);

}
