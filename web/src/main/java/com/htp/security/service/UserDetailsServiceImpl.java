package com.htp.security.service;

import com.htp.domain.Role;
import com.htp.domain.User;
import com.htp.service.role.RoleService;
import com.htp.service.users.UserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final RoleService roleService;

    public UserDetailsServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> byLogin = userService.findByLogin(login);
        User user = byLogin.orElseThrow();
        if (user != null) {
            List<Role> roles = roleService.findAllRolesByUserId(user.getId());
            String collectRoleName = roles.stream().map(Role::getRoleName)
                    .collect(Collectors.joining(","));
            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(),
                    user.getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(collectRoleName)
            );
        } else {
            throw new UsernameNotFoundException("User with login " + login + " was not found");
        }
    }
}
