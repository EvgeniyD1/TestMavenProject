package com.htp.controller;

import com.htp.controller.request.BuildingRequest;
import com.htp.domain.Building;
import com.htp.service.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buildings")
public class BuildingController {

    private BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    public ResponseEntity<List<Building>> findAll() {
        return new ResponseEntity<>(buildingService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Building> findById(@PathVariable("id") Long buildingId) {
        Building building = buildingService.findOne(buildingId);
        return new ResponseEntity<>(building, HttpStatus.OK);
    }

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

    @PostMapping
    public ResponseEntity<Building> createBuilding(@RequestBody BuildingRequest request) {
        Building building = new Building();
        buildingParameter(request, building);
        return new ResponseEntity<>(buildingService.save(building), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Building> updateBuilding(@PathVariable("id") Long buildingId,
                                                   @RequestBody BuildingRequest request){
        Building building = buildingService.findOne(buildingId);
        buildingParameter(request, building);
        return new ResponseEntity<>(buildingService.update(building), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteBuilding(@PathVariable("id") Long buildingId) {
        Building building = buildingService.findOne(buildingId);
        return new ResponseEntity<>(buildingService.delete(building), HttpStatus.OK);
    }

    //???
    @PutMapping("/batch")
    public ResponseEntity<List<Building>> butchBuildingUpdate(List<Long> buildingId,
                                                              @RequestBody List<BuildingRequest> requests){
        List<Building> buildings = new ArrayList<>();
        while (buildingId.iterator().hasNext() && requests.iterator().hasNext()){
            Building building = buildingService.findOne(buildingId.iterator().next());
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
