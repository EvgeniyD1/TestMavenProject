package com.htp.controller.springdata.converter.user;

import com.htp.controller.springdata.users.UserUpdateRequest;
import com.htp.domain.User;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class UserUpdateRequestConverter extends UserRequestConverter<UserUpdateRequest, User> {

    @Override
    public User convert(UserUpdateRequest request){

        User user = ofNullable(entityManager.find(User.class, request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        user.setBlocked(request.isBlocked());

        return doConvert(user, request);
    }
}
