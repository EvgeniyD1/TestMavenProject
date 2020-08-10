package com.htp.controller.springdata.converter.role;

import com.htp.controller.springdata.converter.EntityConverter;
import com.htp.controller.springdata.roles.RoleSaveRequest;
import com.htp.domain.Role;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RoleRequestConverter<S, T> extends EntityConverter<S, T> {

    protected Role doConvert(Role role, RoleSaveRequest request) {

        role.setRoleName(request.getRoleName());

        return role;
    }

}
