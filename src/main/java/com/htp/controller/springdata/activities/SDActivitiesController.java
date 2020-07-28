package com.htp.controller.springdata.activities;

import com.htp.domain.HibernateActivities;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.service.activity.ActivitySDService;
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
@RequestMapping("/sd/activities")
public class SDActivitiesController {

    private final ActivitySDService service;
    private final ConversionService conversionService;

    public SDActivitiesController(ActivitySDService service,
                                  ConversionService conversionService) {
        this.service = service;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "Find all activities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading activities"),
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
    public ResponseEntity<Page<HibernateActivities>> findAll(@ApiIgnore Pageable pageable) {
        Page<HibernateActivities> activities = service.findAll(pageable);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }


    @ApiOperation(value = "Find activities by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading activities"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Activities database id", example = "2", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateActivities> findById(@PathVariable("id") Long activitiesId) {
        Optional<HibernateActivities> optional = service.findById(activitiesId);
        HibernateActivities activities = optional
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }


    @ApiOperation(value = "Find activities by type")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading activities"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "Search query - type", example = "rent",
                    required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/searchByType")
    public ResponseEntity<List<HibernateActivities>> findByType(@RequestParam("type") String query) {
        List<HibernateActivities> byType = service.findByType(query);
        return new ResponseEntity<>(byType, HttpStatus.OK);
    }


    @ApiOperation(value = "Create activities")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation activities"),
            @ApiResponse(code = 422, message = "Failed activities creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header")
    })
    @PostMapping
    public ResponseEntity<HibernateActivities> create(@Valid @RequestBody ActivitySDSaveRequest request,
                                                      @ApiIgnore Principal principal) {
        request.setUserLink("http://localhost:8080/sd/users/searchByLogin?login=" + principal.getName());
        request.setBuildingLink("http://localhost:8080/sd/buildings/");
        HibernateActivities activities = conversionService
                .convert(request, HibernateActivities.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(activities)), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Update activities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful activities update"),
            @ApiResponse(code = 400, message = "Invalid activities ID supplied"),
            @ApiResponse(code = 422, message = "Failed validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Activities database id", example = "20", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateActivities> update(@PathVariable("id") Long activitiesId,
                                                      @Valid @RequestBody ActivitiesSDUpdateRequest request) {
        Optional<HibernateActivities> optional = service.findById(activitiesId);
        HibernateActivities found = optional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        request.setId(found.getId());
        HibernateActivities activities = conversionService.convert(request,
                HibernateActivities.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(activities)), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Activities")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Activities database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long activityId) {
        Optional<HibernateActivities> optional = service.findById(activityId);
        HibernateActivities activities = optional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        service.delete(activities);
        String delete = "Activities with ID = " + activities.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
