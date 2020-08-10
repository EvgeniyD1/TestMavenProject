package com.htp.service.chat;

import com.htp.domain.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatService {

    List<Chat> findByRoomId(Long roomId);

    Optional<Chat> findById(Long chatId);

    Chat save(Chat chat);

    void delete(Chat chat);
}
