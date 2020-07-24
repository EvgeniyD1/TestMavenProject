package com.htp.controller.springdata.converter.user;

import com.htp.controller.springdata.users.UserSDSaveRequest;
import com.htp.domain.hibernate.HibernateRole;
import com.htp.domain.hibernate.HibernateUser;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

@Component
public class UserCreateRequestConverter extends UserRequestConverter<UserSDSaveRequest, HibernateUser> {

    @Override
    public HibernateUser convert(UserSDSaveRequest request) {

        HibernateUser user = new HibernateUser();
        user.setCreated(new Timestamp(new Date().getTime()));

        HibernateRole role = new HibernateRole();
        role.setRoleName("ROLE_USER");
        role.setUser(user);
        user.setRoles(Collections.singleton(role));

        return doConvert(user, request);
    }
}
