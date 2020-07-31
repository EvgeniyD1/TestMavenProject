package com.htp.service.users;

import com.htp.domain.HibernateUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface UserSDService {

    Page<HibernateUser> findAll(Pageable pageable);

    List<HibernateUser> criteriaSpecification(Specification<HibernateUser> spec);

    Optional<HibernateUser> findById(Long userId);

    Optional<HibernateUser> findByLogin(String login);

    HibernateUser save(HibernateUser user);

    void delete(HibernateUser user);
}
