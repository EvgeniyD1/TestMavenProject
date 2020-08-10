package com.htp.controller.springdata.converter.user;

import com.htp.controller.springdata.users.UserSaveRequest;
import com.htp.domain.Role;
import com.htp.domain.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

@Component
public class UserCreateRequestConverter extends UserRequestConverter<UserSaveRequest, User> {

    @Override
    public User convert(UserSaveRequest request) {

        User user = new User();
        user.setCreated(new Timestamp(new Date().getTime()));

        Role role = new Role();
        role.setRoleName("ROLE_USER");
        role.setUser(user);
        user.setRoles(Collections.singleton(role));

        return doConvert(user, request);
    }
}
