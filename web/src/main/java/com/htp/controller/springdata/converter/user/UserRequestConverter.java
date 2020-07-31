package com.htp.controller.springdata.converter.user;

import com.htp.controller.springdata.converter.EntityConverter;
import com.htp.controller.springdata.users.UserSDSaveRequest;
import com.htp.domain.HibernateUser;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.Date;

@Slf4j
public abstract class UserRequestConverter<S, T> extends EntityConverter<S, T> {

    protected HibernateUser doConvert(HibernateUser user, UserSDSaveRequest request) {

        user.setUsername(request.getUsername());
        user.setSurname(request.getSurname());
        user.setPatronymic(request.getPatronymic());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setBirthDate(request.getBirthDate());
        user.setMail(request.getMail());
        user.setCountryLocation(request.getCountryLocation());

        return user;
    }
}
