package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatSDRepository extends CrudRepository<HibernateChat, Long>,
        JpaRepository<HibernateChat, Long>,
        PagingAndSortingRepository<HibernateChat, Long> {

    List<HibernateChat> findByRoomId(Long roomId);

//    HibernateChat save(HibernateChat chat);
//
//    void delete(HibernateChat chat);
}
