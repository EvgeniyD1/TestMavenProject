package com.htp.controller.springdata.buildings;

import com.htp.controller.hibernate.request.BuildingSaveRequest;
import com.htp.dao.springdata.BuildingSDRepository;
import com.htp.domain.hibernate.HibernateBuilding;
import com.htp.exceptions.ResourceNotFoundException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/sd/buildings")
public class SDBuildingController {

    private BuildingSDRepository repository;

    public SDBuildingController(BuildingSDRepository repository) {
        this.repository = repository;
    }


    @ApiOperation(value = "Finding all Buildings")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Buildings"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<List<HibernateBuilding>> findAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Finding Building by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Building"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building database id", example = "2", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateBuilding> findById(@PathVariable("id") Long buildingId) {
        Optional<HibernateBuilding> building = repository.findById(buildingId);
        HibernateBuilding hibBuilding = building.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(hibBuilding, HttpStatus.OK);
    }


    @ApiOperation(value = "Search Buildings by type")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Buildings"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "Search query - type", example = "house",
                    required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/searchType")
    public ResponseEntity<List<HibernateBuilding>> searchType(@RequestParam("type") String type) {
        List<HibernateBuilding> buildings = repository.findByType(type);
        return new ResponseEntity<>(buildings, HttpStatus.OK);
    }


    @ApiOperation(value = "Create Building")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation Building"),
            @ApiResponse(code = 422, message = "Failed Building creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header")
//    })
    @PostMapping
    public ResponseEntity<HibernateBuilding> create(@Valid @RequestBody BuildingSaveRequest request) {
        HibernateBuilding building = HibernateBuilding.builder()
                .type(request.getType())
                .landArea(request.getLandArea())
                .roomsCount(request.getRoomsCount())
                .totalRoomsArea(request.getLivingArea())
                .livingArea(request.getLivingArea())
                .kitchenArea(request.getKitchenArea())
                .buildingFloors(request.getBuildingFloors())
                .floor(request.getFloor())
                .buildingYear(request.getBuildingYear())
                .repairs(request.isRepairs())
                .garage(request.isGarage())
                .barn(request.isBarn())
                .bath(request.isBath())
                .description(request.getDescription())
                .countryLocation(request.getCountryLocation())
                .regionLocation(request.getRegionLocation())
                .townLocation(request.getTownLocation())
                .streetLocation(request.getStreetLocation())
                .buildingLocation(request.getBuildingLocation())
                .roomLocation(request.getRoomLocation())
                .build();
        return new ResponseEntity<>(repository.save(building), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Update Building")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Building update"),
            @ApiResponse(code = 400, message = "Invalid Building ID supplied"),
            @ApiResponse(code = 422, message = "Failed validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Building database id", example = "2", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateBuilding> update(@Valid @PathVariable("id") Long buildingId,
                                                    @RequestBody BuildingSaveRequest request) {
        Optional<HibernateBuilding> buildingOptional = repository.findById(buildingId);
        HibernateBuilding building = buildingOptional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
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
        return new ResponseEntity<>(repository.save(building), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Building")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Building database id", example = "100", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long buildingId) {
        Optional<HibernateBuilding> buildingOptional = repository.findById(buildingId);
        HibernateBuilding building = buildingOptional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        repository.delete(building);
        String delete = "Building with ID = " + building.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
