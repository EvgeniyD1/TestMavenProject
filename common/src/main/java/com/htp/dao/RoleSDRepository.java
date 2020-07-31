package com.htp.dao;

import com.htp.domain.HibernateRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleSDRepository extends CrudRepository<HibernateRole, Long>,
        JpaRepository<HibernateRole, Long>,
        PagingAndSortingRepository<HibernateRole, Long> {

    Page<HibernateRole> findAll(Pageable pageable);

    Optional<HibernateRole> findById(Long userId);

    @Query("select role from HibernateRole role join role.user user where user.id = :id")
    List<HibernateRole> findAllRolesByUserId(@Param("id") Long userId);

}
