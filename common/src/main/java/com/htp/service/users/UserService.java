package com.htp.service.users;

import com.htp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Page<User> findAll(Pageable pageable);

    List<User> criteriaSpecification(Specification<User> spec);

    Optional<User> findById(Long userId);

    Optional<User> findByLogin(String login);

    User save(User user);

    void delete(User user);
}
