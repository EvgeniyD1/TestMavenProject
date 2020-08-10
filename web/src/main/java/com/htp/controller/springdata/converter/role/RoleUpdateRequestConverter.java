package com.htp.controller.springdata.converter.role;

import com.htp.controller.springdata.roles.RoleUpdateRequest;
import com.htp.domain.Role;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleUpdateRequestConverter extends RoleRequestConverter<RoleUpdateRequest, Role>{

    @Override
    public Role convert(RoleUpdateRequest request) {

        Role role = Optional.ofNullable(entityManager.find(Role.class, request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        return doConvert(role, request);
    }
}
