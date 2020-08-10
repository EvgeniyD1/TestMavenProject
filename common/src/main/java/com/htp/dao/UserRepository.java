package com.htp.dao;

import com.htp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>,
        JpaRepository<User, Long>,
        PagingAndSortingRepository<User, Long>,
        JpaSpecificationExecutor<User> {

    Optional<User> findByLogin(String login);

}
