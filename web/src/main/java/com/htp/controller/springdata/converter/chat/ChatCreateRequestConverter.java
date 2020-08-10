package com.htp.controller.springdata.converter.chat;

import com.htp.controller.springdata.chat.ChatSaveRequest;
import com.htp.domain.Chat;
import com.htp.domain.User;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChatCreateRequestConverter extends ChatRequestConverter<ChatSaveRequest, Chat>{

    @Override
    public Chat convert(ChatSaveRequest request) {

        Chat chat = new Chat();

        User user = Optional.ofNullable(entityManager.find(User.class, request.getUserId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        chat.setUser(user);
        chat.setRoomId(request.getRoomId());

        return doConvert(chat, request);
    }
}
