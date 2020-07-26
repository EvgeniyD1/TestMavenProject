package com.htp.service.springdata.chat;

import com.htp.dao.springdata.ChatSDRepository;
import com.htp.domain.hibernate.HibernateChat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatSDServiceImpl implements ChatSDService{

    private final ChatSDRepository repository;

    public ChatSDServiceImpl(ChatSDRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HibernateChat> findByRoomId(Long roomId) {
        return repository.findByRoomId(roomId);
    }

    @Override
    public Optional<HibernateChat> findById(Long chatId) {
        return repository.findById(chatId);
    }

    @Override
    public HibernateChat save(HibernateChat chat) {
        return repository.save(chat);
    }

    @Override
    public void delete(HibernateChat chat) {
        repository.delete(chat);
    }
}
