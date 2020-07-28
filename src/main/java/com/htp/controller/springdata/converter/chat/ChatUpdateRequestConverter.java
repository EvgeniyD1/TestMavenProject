package com.htp.controller.springdata.converter.chat;

import com.htp.controller.springdata.chat.ChatSDUpdateRequest;
import com.htp.domain.HibernateChat;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChatUpdateRequestConverter extends ChatRequestConverter<ChatSDUpdateRequest, HibernateChat>{

    @Override
    public HibernateChat convert(ChatSDUpdateRequest request) {

        HibernateChat chat = Optional.ofNullable(entityManager.find(HibernateChat.class, request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        return doConvert(chat, request);
    }
}
