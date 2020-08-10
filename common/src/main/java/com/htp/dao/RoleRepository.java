package com.htp.dao;

import com.htp.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>,
        JpaRepository<Role, Long>,
        PagingAndSortingRepository<Role, Long> {

    @Query("select role from Role role join role.user user where user.id = :id")
    List<Role> findAllRolesByUserId(@Param("id") Long userId);

}
