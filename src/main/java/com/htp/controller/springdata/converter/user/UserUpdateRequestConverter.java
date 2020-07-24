package com.htp.controller.springdata.converter.user;

import com.htp.controller.springdata.users.UserSDUpdateRequest;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class UserUpdateRequestConverter extends UserRequestConverter<UserSDUpdateRequest, HibernateUser> {

    @Override
    public HibernateUser convert(UserSDUpdateRequest request){

        HibernateUser user = ofNullable(entityManager.find(HibernateUser.class, request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        user.setBlocked(request.isBlocked());

        return doConvert(user, request);
    }
}
