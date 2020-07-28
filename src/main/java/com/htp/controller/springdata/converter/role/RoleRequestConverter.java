package com.htp.controller.springdata.converter.role;

import com.htp.controller.springdata.converter.EntityConverter;
import com.htp.controller.springdata.roles.RoleSDSaveRequest;
import com.htp.domain.HibernateRole;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RoleRequestConverter<S, T> extends EntityConverter<S, T> {

    protected HibernateRole doConvert(HibernateRole role, RoleSDSaveRequest request) {

        role.setRoleName(request.getRoleName());

        return role;
    }

}
