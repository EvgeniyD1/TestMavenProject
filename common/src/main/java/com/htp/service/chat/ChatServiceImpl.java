package com.htp.service.chat;

import com.htp.dao.ChatRepository;
import com.htp.domain.Chat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository repository;

    public ChatServiceImpl(ChatRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Chat> findByRoomId(Long roomId) {
        return repository.findByRoomId(roomId);
    }

    @Override
    public Optional<Chat> findById(Long chatId) {
        return repository.findById(chatId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Chat save(Chat chat) {
        return repository.save(chat);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(Chat chat) {
        repository.delete(chat);
    }
}
