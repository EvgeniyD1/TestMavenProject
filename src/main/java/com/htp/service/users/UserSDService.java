package com.htp.service.users;

import com.htp.domain.HibernateUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserSDService {

    Page<HibernateUser> findAll(Pageable pageable);

    Optional<HibernateUser> findById(Long userId);

    Optional<HibernateUser> findByLogin(String login);

    HibernateUser save(HibernateUser user);

    void delete(HibernateUser user);
}
