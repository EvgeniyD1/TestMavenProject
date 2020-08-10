package com.htp.service.building;

import com.htp.dao.BuildingRepository;
import com.htp.domain.Building;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"cache"})
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository repository;

    public BuildingServiceImpl(BuildingRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable
    public Page<Building> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Building> criteriaSpecification(Specification<Building> spec) {
        return repository.findAll(spec);
    }

    @Override
    public Optional<Building> findById(Long buildingId) {
        return repository.findById(buildingId);
    }

    @Override
    @Cacheable
    public List<Building> findByType(String type) {
        return repository.findByType(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Building save(Building building) {
        return repository.save(building);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void delete(Building building) {
        repository.delete(building);
    }
}
