package com.htp.controller.springdata.buildings;

import com.htp.dao.criteria.SpecificationBuilder;
import com.htp.domain.Building;
import com.htp.domain.User;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.service.building.BuildingService;
import com.htp.service.users.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@Transactional
@RequestMapping("/buildings")
public class BuildingController {

    private final BuildingService service;
    private final UserService userService;
    private final ConversionService conversionService;

    public BuildingController(BuildingService service,
                              UserService userService,
                              ConversionService conversionService) {
        this.service = service;
        this.userService = userService;
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
    public ResponseEntity<Page<Building>> findAll(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }


    @ApiOperation(value = "Find Building by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Building"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building database id", example = "36", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Building> findById(@PathVariable("id") Long buildingId) {
        Optional<Building> buildingOptional = service.findById(buildingId);
        Building building = buildingOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(building, HttpStatus.OK);
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
    public ResponseEntity<List<Building>> searchType(@RequestParam("type") String type) {
        List<Building> buildings = service.findByType(type);
        return new ResponseEntity<>(buildings, HttpStatus.OK);
    }


    @ApiOperation(value = "Search Buildings by criteria")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Buildings"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "Search query - (key)(<:>)(value),('?)(key)(<:>)(value)",
                    example = "type:flat,roomsCount>2", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/searchCriteria")
    public ResponseEntity<List<Building>> search(@RequestParam("search") String search) {
        SpecificationBuilder<Building> builder = new SpecificationBuilder<>();
        Pattern pattern = Pattern.compile("('?)(\\w+?)([:<>])(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
        }
        Specification<Building> spec = builder.build();
        List<Building> buildings = service.criteriaSpecification(spec);
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
    public ResponseEntity<Building> create(@Valid @RequestBody BuildingSaveRequest request,
                                           @ApiIgnore Principal principal) {
        Optional<User> userOptional = userService.findByLogin(principal.getName());
        User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        request.setUserId(user.getId());
        Building building = conversionService.convert(request, Building.class);
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
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Building database id", example = "35", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Building> update(@PathVariable("id") Long buildingId,
                                           @Valid @RequestBody BuildingUpdateRequest request) {
        Optional<Building> buildingOptional = service.findById(buildingId);
        Building found = buildingOptional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        request.setId(found.getId());
        Building building = conversionService.convert(request, Building.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(building)), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Building")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Building database id", example = "38", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long buildingId) {
        Optional<Building> buildingOptional = service.findById(buildingId);
        Building building = buildingOptional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        service.delete(building);
        String delete = "Building with ID = " + building.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
