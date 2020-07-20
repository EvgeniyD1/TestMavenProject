package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleSDRepository extends CrudRepository<HibernateRole, Long>,
        JpaRepository<HibernateRole, Long>,
        PagingAndSortingRepository<HibernateRole, Long> {

    @Query("select role from HibernateRole role join role.user user where user.id = :id")
    List<HibernateRole> findAllRolesByUserId(@Param("id") Long userId);

}
