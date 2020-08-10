package com.htp.controller.springdata.converter.role;

import com.htp.controller.springdata.roles.RoleSaveRequest;
import com.htp.domain.Role;
import com.htp.domain.User;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleCreateRequestConverter extends RoleRequestConverter<RoleSaveRequest, Role>{

    @Override
    public Role convert(RoleSaveRequest request) {

        Role role = new Role();
        User user = Optional.ofNullable(entityManager.find(User.class, request.getUserId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        role.setUser(user);

        return doConvert(role, request);
    }
}
