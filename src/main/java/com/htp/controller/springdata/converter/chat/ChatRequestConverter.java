package com.htp.controller.springdata.converter.chat;

import com.htp.controller.springdata.chat.ChatSDSaveRequest;
import com.htp.controller.springdata.converter.EntityConverter;
import com.htp.domain.hibernate.HibernateChat;

import java.sql.Timestamp;
import java.util.Date;

public abstract class ChatRequestConverter<S, T> extends EntityConverter<S, T> {

    protected HibernateChat doConvert(HibernateChat chat,
                                      ChatSDSaveRequest request) {

        chat.setRoomId(request.getRoomId());
        chat.setTime(new Timestamp(new Date().getTime()));
        chat.setMessage(request.getMessage());

        return chat;
    }

}
