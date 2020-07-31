package com.htp.dao;

import com.htp.domain.HibernateChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatSDRepository extends CrudRepository<HibernateChat, Long>,
        JpaRepository<HibernateChat, Long>,
        PagingAndSortingRepository<HibernateChat, Long> {

    List<HibernateChat> findByRoomId(Long roomId);

    Optional<HibernateChat> findById(Long chatId);

}
