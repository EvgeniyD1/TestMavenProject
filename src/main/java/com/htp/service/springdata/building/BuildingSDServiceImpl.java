package com.htp.service.springdata.building;

import com.htp.dao.springdata.BuildingSDRepository;
import com.htp.domain.hibernate.HibernateBuilding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingSDServiceImpl implements BuildingSDService{

    private final BuildingSDRepository repository;

    public BuildingSDServiceImpl(BuildingSDRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<HibernateBuilding> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<HibernateBuilding> findById(Long buildingId) {
        return repository.findById(buildingId);
    }

    @Override
    public List<HibernateBuilding> findByType(String type) {
        return repository.findByType(type);
    }

    @Override
    public HibernateBuilding save(HibernateBuilding building) {
        return repository.save(building);
    }

    @Override
    public void delete(HibernateBuilding building) {
        repository.delete(building);
    }
}
