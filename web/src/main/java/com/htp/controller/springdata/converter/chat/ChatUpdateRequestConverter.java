package com.htp.controller.springdata.converter.chat;

import com.htp.controller.springdata.chat.ChatUpdateRequest;
import com.htp.domain.Chat;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChatUpdateRequestConverter extends ChatRequestConverter<ChatUpdateRequest, Chat>{

    @Override
    public Chat convert(ChatUpdateRequest request) {

        Chat chat = Optional.ofNullable(entityManager.find(Chat.class, request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        return doConvert(chat, request);
    }
}
