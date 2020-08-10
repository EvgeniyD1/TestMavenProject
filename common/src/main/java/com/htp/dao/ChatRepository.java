package com.htp.dao;

import com.htp.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long>,
        JpaRepository<Chat, Long>,
        PagingAndSortingRepository<Chat, Long> {

    List<Chat> findByRoomId(Long roomId);

}
