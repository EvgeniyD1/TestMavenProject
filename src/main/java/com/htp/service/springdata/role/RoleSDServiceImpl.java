package com.htp.service.springdata.role;

import com.htp.dao.springdata.RoleSDRepository;
import com.htp.domain.hibernate.HibernateRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleSDServiceImpl implements RoleSDService{

    private final RoleSDRepository repository;

    public RoleSDServiceImpl(RoleSDRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<HibernateRole> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<HibernateRole> findById(Long userId) {
        return repository.findById(userId);
    }

    @Override
    public List<HibernateRole> findAllRolesByUserId(Long userId) {
        return repository.findAllRolesByUserId(userId);
    }

    @Override
    public HibernateRole save(HibernateRole user) {
        return repository.save(user);
    }

    @Override
    public void delete(HibernateRole user) {
        repository.delete(user);
    }
}
