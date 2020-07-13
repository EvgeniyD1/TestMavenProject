package com.htp.controller.hibernate.controller;

import com.htp.controller.hibernate.request.BuildingSaveRequest;
import com.htp.domain.hibernate.HibernateBuilding;
import com.htp.service.hibernate.HibernateBuildingService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@Transactional
@RequestMapping("/hibernateBuildings")
public class HibernateBuildingController {

    private HibernateBuildingService hibernateBuildingService;

    public HibernateBuildingController(HibernateBuildingService hibernateBuildingService) {
        this.hibernateBuildingService = hibernateBuildingService;
    }


    @ApiOperation(value = "Finding all Buildings")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Buildings"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header")
//    })
    @GetMapping
    public ResponseEntity<List<HibernateBuilding>> findAll() {
        return new ResponseEntity<>(hibernateBuildingService.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Finding Building by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Building"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Building database id", example = "2", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateBuilding> findById(@PathVariable("id") Long buildingId) {
        HibernateBuilding building = hibernateBuildingService.findOne(buildingId);
        if (building == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Building with ID = " + buildingId + " not found");
        }
        return new ResponseEntity<>(building, HttpStatus.OK);
    }


    @ApiOperation(value = "Search users by Country, Town and Street")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Building"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "country", value = "Search query - country", example = "Belarus",
                    required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "town", value = "Search query - town, if empty then 0",
                    example = "Minsk", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "street", value = "Search query - street, if empty then 0",
                    example = "Komsomolskaya", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/searchLocation")
    public ResponseEntity<List<HibernateBuilding>> searchLocation(@RequestParam("country") String country,
                                                                  @RequestParam("town") String town,
                                                                  @RequestParam("street") String street) {
        List<HibernateBuilding> buildings = hibernateBuildingService.searchLocation(country, town, street);
        if (buildings.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Buildings with country '" + country
                    + "' , town '" + town + "' and street '" + street + "' not found");
        }
        return new ResponseEntity<>(buildings, HttpStatus.OK);
    }


    @ApiOperation(value = "Search Buildings by type")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Buildings"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "type", value = "Search query - type", example = "house",
                    required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/searchType")
    public ResponseEntity<List<HibernateBuilding>> searchType(@RequestParam("type") String query) {
        List<HibernateBuilding> buildings = hibernateBuildingService.searchType(query);
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
        return new ResponseEntity<>(hibernateBuildingService.save(building), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Update Building")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Building update"),
            @ApiResponse(code = 400, message = "Invalid Building ID supplied"),
            @ApiResponse(code = 404, message = "Building was not found"),
            @ApiResponse(code = 422, message = "Failed Building creation properties validation"),
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
        HibernateBuilding building = hibernateBuildingService.findOne(buildingId);
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
        return new ResponseEntity<>(hibernateBuildingService.update(building), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete user")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Building database id", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long buildingId) {
        HibernateBuilding buildingForDelete = hibernateBuildingService.findOne(buildingId);
        long db = hibernateBuildingService.delete(buildingForDelete);
        String delete = "Building with ID = " + db + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
