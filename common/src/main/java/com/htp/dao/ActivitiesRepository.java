package com.htp.dao;

import com.htp.domain.Activities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivitiesRepository extends CrudRepository<Activities, Long>,
        JpaRepository<Activities, Long>,
        PagingAndSortingRepository<Activities, Long>,
        JpaSpecificationExecutor<Activities> {

    List<Activities> findByType(String type);

}
