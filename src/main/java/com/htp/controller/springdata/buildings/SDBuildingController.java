package com.htp.controller.springdata.buildings;

import com.htp.domain.hibernate.HibernateBuilding;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.service.springdata.building.BuildingSDService;
import com.htp.service.springdata.users.UserSDService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/sd/buildings")
public class SDBuildingController {

    private final BuildingSDService service;
    private final UserSDService userSDService;
    private final ConversionService conversionService;

    public SDBuildingController(BuildingSDService service,
                                UserSDService userSDService,
                                ConversionService conversionService) {
        this.service = service;
        this.userSDService = userSDService;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "Finding all Buildings")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Buildings"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "Page number",
                    example = "0", defaultValue = "0", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Items per page",
                    example = "3", defaultValue = "3", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "Field to sort",
                    example = "id", defaultValue = "id", dataType = "string", paramType = "query")
    })
    @GetMapping
    public ResponseEntity<Page<HibernateBuilding>> findAll(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }


    @ApiOperation(value = "Find Building by ID")
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
        Optional<HibernateBuilding> building = service.findById(buildingId);
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
        List<HibernateBuilding> buildings = service.findByType(type);
        return new ResponseEntity<>(buildings, HttpStatus.OK);
    }


    @ApiOperation(value = "Create Building")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation Building"),
            @ApiResponse(code = 422, message = "Failed Building creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header")
    })
    @PostMapping
    public ResponseEntity<HibernateBuilding> create(@Valid @RequestBody BuildingSDSaveRequest request,
                                                    @ApiIgnore Principal principal) {
        Optional<HibernateUser> userOptional = userSDService.findByLogin(principal.getName());
        HibernateUser user = userOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        request.setUserId(user.getId());
        HibernateBuilding building = conversionService.convert(request, HibernateBuilding.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(building)), HttpStatus.CREATED);
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
    public ResponseEntity<HibernateBuilding> update(@PathVariable("id") Long buildingId,
                                                    @Valid @RequestBody BuildingSDUpdateRequest request) {
        Optional<HibernateBuilding> buildingOptional = service.findById(buildingId);
        HibernateBuilding found = buildingOptional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        request.setId(found.getId());
        HibernateBuilding building = conversionService.convert(request, HibernateBuilding.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(building)), HttpStatus.OK);
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
        Optional<HibernateBuilding> buildingOptional = service.findById(buildingId);
        HibernateBuilding building = buildingOptional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        service.delete(building);
        String delete = "Building with ID = " + building.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
