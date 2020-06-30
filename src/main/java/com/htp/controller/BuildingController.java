package com.htp.controller;

import com.htp.controller.request.BuildingRequest;
import com.htp.controller.request.IdRequest;
import com.htp.domain.Building;
import com.htp.service.BuildingService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buildings")
public class BuildingController {

    private BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @ApiOperation(value = "Finding all buildings")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading buildings"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<List<Building>> findAll() {
        return new ResponseEntity<>(buildingService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Finding building by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading building"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building database id", example = "1", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Building> findById(@PathVariable("id") Long buildingId) {
        Building building = buildingService.findOne(buildingId);
        return new ResponseEntity<>(building, HttpStatus.OK);
    }

    @ApiOperation(value = "Search buildings by type")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading buildings"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "query", value = "Search query - free text", example = "house",
                    required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/search")
    public ResponseEntity<List<Building>> searchBuilding(@RequestParam("query") String query) {
        List<Building> searchResult = buildingService.search(query);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

    private void buildingParameter(@RequestBody BuildingRequest request, Building building) {
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
    }

    @ApiOperation(value = "Create building")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation building"),
            @ApiResponse(code = 422, message = "Failed building creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    public ResponseEntity<Building> createBuilding(@Valid @RequestBody BuildingRequest request) {
        Building building = new Building();
        buildingParameter(request, building);
        return new ResponseEntity<>(buildingService.save(building), HttpStatus.OK);
    }

    @ApiOperation(value = "Update building")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful building update"),
            @ApiResponse(code = 400, message = "Invalid building ID supplied"),
            @ApiResponse(code = 404, message = "Building was not found"),
            @ApiResponse(code = 422, message = "Failed building update properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Building> updateBuilding(@Valid @PathVariable("id") Long buildingId,
                                                   @RequestBody BuildingRequest request){
        Building building = buildingService.findOne(buildingId);
        buildingParameter(request, building);
        return new ResponseEntity<>(buildingService.update(building), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete building")
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteBuilding(@PathVariable("id") Long buildingId) {
        Building building = buildingService.findOne(buildingId);
        return new ResponseEntity<>(buildingService.delete(building), HttpStatus.OK);
    }

    //???
    @ApiOperation(value = "Batch update building")
    @PutMapping("/batch")
    public ResponseEntity<List<Building>> butchBuildingUpdate(@RequestBody List<IdRequest> buildingId,
                                                              @RequestBody List<BuildingRequest> requests){
        List<Building> buildings = new ArrayList<>();
        while (buildingId.iterator().hasNext() && requests.iterator().hasNext()){
            Building building = buildingService.findOne(buildingId.iterator().next().getId());
            BuildingRequest request = requests.iterator().next();
            buildingParameter(request, building);
            buildings.add(building);
        }
        return new ResponseEntity<>(buildingService.batchUpdate(buildings), HttpStatus.OK);
    }

//    @GetMapping
//    public String findAll(ModelMap modelMap){
//        modelMap.addAttribute("buildings", buildingService.findAll());
//        return "building/buildings";
//    }
//
//    @GetMapping("/{id}")
//    public String findById(@PathVariable("id") Long buildingId, ModelMap modelMap){
//        modelMap.addAttribute("building", buildingService.findOne(buildingId));
//        return "building/building";
//    }
//
//    @GetMapping("/search")
//    public String searchBuilding(@RequestParam("query") String query, ModelMap modelMap){
//        modelMap.addAttribute("buildings",buildingService.search(query));
//        return "building/buildings";
//    }

}
