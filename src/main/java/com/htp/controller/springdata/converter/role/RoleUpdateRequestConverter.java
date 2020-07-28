package com.htp.controller.springdata.converter.role;

import com.htp.controller.springdata.roles.RoleSDUpdateRequest;
import com.htp.domain.HibernateRole;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleUpdateRequestConverter extends RoleRequestConverter<RoleSDUpdateRequest, HibernateRole>{

    @Override
    public HibernateRole convert(RoleSDUpdateRequest request) {

        HibernateRole role = Optional.ofNullable(entityManager.find(HibernateRole.class, request.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

        return doConvert(role, request);
    }
}
