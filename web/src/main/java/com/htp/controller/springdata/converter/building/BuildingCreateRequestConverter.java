package com.htp.controller.springdata.converter.building;

import com.htp.controller.springdata.buildings.BuildingSaveRequest;
import com.htp.domain.Building;
import com.htp.domain.User;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BuildingCreateRequestConverter extends BuildingRequestConverter<BuildingSaveRequest, Building>{

    @Override
    public Building convert(BuildingSaveRequest request) {

        Building building = new Building();
        User user = Optional.ofNullable(entityManager.find(User.class, request.getUserId()))
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        building.setUser(user);

        return doConvert(building, request);
    }
}
