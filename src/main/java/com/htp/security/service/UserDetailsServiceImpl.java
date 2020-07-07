package com.htp.security.service;

import com.htp.dao.RoleDao;
import com.htp.dao.UserDao;
import com.htp.domain.Role;
import com.htp.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;
    private RoleDao roleDao;

    public UserDetailsServiceImpl(@Qualifier("userRepositoryJdbcTemplate") UserDao userDao,
                                  @Qualifier("roleRepositoryJdbcTemplate") RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User userFindByLogin = userDao.findByLogin(login);
        if (userFindByLogin != null) {
            List<Role> userAuthorities = roleDao.findAllRole(userFindByLogin.getId());
            String collectRoleName = userAuthorities.stream().map(Role::getRoleName)
                    .collect(Collectors.joining(","));
            return new org.springframework.security.core.userdetails.User(
                    userFindByLogin.getLogin(),
                    userFindByLogin.getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(collectRoleName)
            );
        } else {
            throw new UsernameNotFoundException("User with login " + login + " was not found");
        }
    }
}
