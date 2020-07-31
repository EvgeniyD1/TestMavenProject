package com.htp.controller.springdata.converter.role;

import com.htp.controller.springdata.roles.RoleSDSaveRequest;
import com.htp.domain.HibernateRole;
import com.htp.domain.HibernateUser;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleCreateRequestConverter extends RoleRequestConverter<RoleSDSaveRequest, HibernateRole>{

    @Override
    public HibernateRole convert(RoleSDSaveRequest request) {

        HibernateRole role = new HibernateRole();
        HibernateUser user = Optional.ofNullable(entityManager.find(HibernateUser.class, request.getUserId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        role.setUser(user);

        return doConvert(role, request);
    }
}
