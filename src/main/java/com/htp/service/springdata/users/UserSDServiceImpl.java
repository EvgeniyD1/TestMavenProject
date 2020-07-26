package com.htp.service.springdata.users;

import com.htp.dao.springdata.UserSDRepository;
import com.htp.domain.hibernate.HibernateUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSDServiceImpl implements UserSDService {

    private final UserSDRepository repository;

    public UserSDServiceImpl(UserSDRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<HibernateUser> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<HibernateUser> findById(Long userId) {
        return repository.findById(userId);
    }

    @Override
    public Optional<HibernateUser> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public HibernateUser save(HibernateUser user) {
        return repository.save(user);
    }

    @Override
    public void delete(HibernateUser user) {
        repository.delete(user);
    }
}
