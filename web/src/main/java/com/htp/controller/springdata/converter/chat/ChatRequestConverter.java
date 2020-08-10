package com.htp.controller.springdata.converter.chat;

import com.htp.controller.springdata.chat.ChatRequest;
import com.htp.controller.springdata.converter.EntityConverter;
import com.htp.domain.Chat;

import java.sql.Timestamp;
import java.util.Date;

public abstract class ChatRequestConverter<S, T> extends EntityConverter<S, T> {

    protected Chat doConvert(Chat chat,
                             ChatRequest request) {

        chat.setTime(new Timestamp(new Date().getTime()));
        chat.setMessage(request.getMessage());

        return chat;
    }

}
