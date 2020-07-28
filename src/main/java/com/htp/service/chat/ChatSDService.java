package com.htp.service.chat;

import com.htp.domain.HibernateChat;

import java.util.List;
import java.util.Optional;

public interface ChatSDService {

    List<HibernateChat> findByRoomId(Long roomId);

    Optional<HibernateChat> findById(Long chatId);

    HibernateChat save(HibernateChat chat);

    void delete(HibernateChat chat);
}
