package com.htp.controller.springdata.converter.building;

import com.htp.controller.springdata.buildings.BuildingSDSaveRequest;
import com.htp.controller.springdata.converter.EntityConverter;
import com.htp.domain.hibernate.HibernateBuilding;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BuildingRequestConverter <S, T> extends EntityConverter<S, T> {

    protected HibernateBuilding doConvert(HibernateBuilding building, BuildingSDSaveRequest request) {

        building.setType(request.getType());
        building.setLandArea(request.getLandArea());
        building.setRoomsCount(request.getRoomsCount());
        building.setTotalRoomsArea(request.getTotalRoomsArea());
        building.setLivingArea(request.getLivingArea());
        building.setKitchenArea(request.getKitchenArea());
        building.setBuildingFloors(request.getBuildingFloors());
        building.setFloor(request.getFloor());
        building.setBuildingYear(request.getBuildingYear());
        building.setRepairs(request.isRepairs());
        building.setGarage(request.isGarage());
        building.setBarn(request.isBarn());
        building.setBath(request.isBath());
        building.setDescription(request.getDescription());
        building.setCountryLocation(request.getCountryLocation());
        building.setRegionLocation(request.getRegionLocation());
        building.setTownLocation(request.getTownLocation());
        building.setStreetLocation(request.getStreetLocation());
        building.setBuildingLocation(request.getBuildingLocation());
        building.setRoomLocation(request.getRoomLocation());

        return building;
    }
}
