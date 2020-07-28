package com.htp.controller.springdata.converter.chat;

import com.htp.controller.springdata.chat.ChatSDSaveRequest;
import com.htp.domain.HibernateChat;
import com.htp.domain.HibernateUser;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChatCreateRequestConverter extends ChatRequestConverter<ChatSDSaveRequest, HibernateChat>{

    @Override
    public HibernateChat convert(ChatSDSaveRequest request) {

        HibernateChat chat = new HibernateChat();

        HibernateUser user = Optional.ofNullable(entityManager.find(HibernateUser.class, request.getUserId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        chat.setUser(user);
        chat.setRoomId(request.getRoomId());

        return doConvert(chat, request);
    }
}
