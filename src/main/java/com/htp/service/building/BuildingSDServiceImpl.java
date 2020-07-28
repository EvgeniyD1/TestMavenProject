package com.htp.service.building;

import com.htp.dao.BuildingSDRepository;
import com.htp.domain.HibernateBuilding;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"cache"})
public class BuildingSDServiceImpl implements BuildingSDService{

    private final BuildingSDRepository repository;

    public BuildingSDServiceImpl(BuildingSDRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable
    public Page<HibernateBuilding> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Cacheable
    public Optional<HibernateBuilding> findById(Long buildingId) {
        return repository.findById(buildingId);
    }

    @Override
    @Cacheable
    public List<HibernateBuilding> findByType(String type) {
        return repository.findByType(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateBuilding save(HibernateBuilding building) {
        return repository.save(building);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(HibernateBuilding building) {
        repository.delete(building);
    }
}
