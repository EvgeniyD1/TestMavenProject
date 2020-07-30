package com.htp.service.chat;

import com.htp.dao.ChatSDRepository;
import com.htp.domain.HibernateChat;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"cache"})
public class ChatSDServiceImpl implements ChatSDService{

    private final ChatSDRepository repository;

    public ChatSDServiceImpl(ChatSDRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable
    public List<HibernateChat> findByRoomId(Long roomId) {
        return repository.findByRoomId(roomId);
    }

    @Override
    public Optional<HibernateChat> findById(Long chatId) {
        return repository.findById(chatId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateChat save(HibernateChat chat) {
        return repository.save(chat);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(HibernateChat chat) {
        repository.delete(chat);
    }
}
