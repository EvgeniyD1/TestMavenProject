package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomSDRepository extends CrudRepository<HibernateRoom, Long>,
        JpaRepository<HibernateRoom, Long>,
        PagingAndSortingRepository<HibernateRoom, Long> {

    @Query("select room from HibernateRoom room join room.building building where building.id = :id")
    List<HibernateRoom> findAllRoomByBuildingId(@Param("id") Long buildingId);
}
