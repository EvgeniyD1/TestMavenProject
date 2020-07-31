package com.htp.security.service;

import com.htp.domain.HibernateRole;
import com.htp.domain.HibernateUser;
import com.htp.service.role.RoleSDService;
import com.htp.service.users.UserSDService;
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

    private final UserSDService userSDService;
    private final RoleSDService roleSDService;

    public UserDetailsServiceImpl(UserSDService userSDService, RoleSDService roleSDService) {
        this.userSDService = userSDService;
        this.roleSDService = roleSDService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<HibernateUser> byLogin = userSDService.findByLogin(login);
        HibernateUser user = byLogin.orElseThrow();
        if (user != null) {
            List<HibernateRole> roles = roleSDService.findAllRolesByUserId(user.getId());
            String collectRoleName = roles.stream().map(HibernateRole::getRoleName)
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
