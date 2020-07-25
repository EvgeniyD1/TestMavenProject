package com.htp.controller.springdata.activitiestequest;

import com.htp.dao.springdata.RealEstateActivitiesRequestSDRepository;
import com.htp.domain.hibernate.HibernateRealEstateActivitiesRequest;
import com.htp.exceptions.ResourceNotFoundException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
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
@RequestMapping("/sd/activities_request")
public class SDREActivitiesRequestController {

    private final RealEstateActivitiesRequestSDRepository repository;
    private final ConversionService conversionService;

    public SDREActivitiesRequestController(RealEstateActivitiesRequestSDRepository repository,
                                           ConversionService conversionService) {
        this.repository = repository;
        this.conversionService = conversionService;
    }


    @ApiOperation(value = "Find activities request by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading activities request"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Activities request database id", example = "2", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateRealEstateActivitiesRequest> findById(@PathVariable("id") Long activitiesReqId) {
        Optional<HibernateRealEstateActivitiesRequest> optional = repository.findById(activitiesReqId);
        HibernateRealEstateActivitiesRequest request = optional
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(request, HttpStatus.OK);
    }


    @ApiOperation(value = "Finding all activities request by activitiesId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading activities request"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activitiesId", value = "Activities database id", example = "2", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/activities/{activitiesId}")
    public ResponseEntity<List<HibernateRealEstateActivitiesRequest>> findAllUserRoles(
            @PathVariable("activitiesId") Long activitiesId) {
        List<HibernateRealEstateActivitiesRequest> roles = repository.findAllRolesByUserId(activitiesId);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


    @ApiOperation(value = "Create activities request")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation activities request"),
            @ApiResponse(code = 422, message = "Failed activities request creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header")
    })
    @PostMapping
    public ResponseEntity<HibernateRealEstateActivitiesRequest> create(
            @Valid @RequestBody REActivitiesRequestSDSaveRequest request,
            @ApiIgnore Principal principal) {
        request.setUserLink("http://localhost:8080/sd/users/searchByLogin?login=" + principal.getName());
        HibernateRealEstateActivitiesRequest activitiesRequest = conversionService
                .convert(request, HibernateRealEstateActivitiesRequest.class);
        return new ResponseEntity<>(repository.save(Objects.requireNonNull(activitiesRequest)), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Update activities request")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful activities request update"),
            @ApiResponse(code = 400, message = "Invalid activities request ID supplied"),
            @ApiResponse(code = 422, message = "Failed validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Activities request database id", example = "20", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateRealEstateActivitiesRequest> update(
            @PathVariable("id") Long activitiesRequestId,
            @Valid @RequestBody REActivitiesRequestSDUpdateRequest request) {
        Optional<HibernateRealEstateActivitiesRequest> optional = repository.findById(activitiesRequestId);
        HibernateRealEstateActivitiesRequest found = optional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        request.setId(found.getId());
        HibernateRealEstateActivitiesRequest activitiesRequest = conversionService.convert(request,
                HibernateRealEstateActivitiesRequest.class);
        return new ResponseEntity<>(repository.save(Objects.requireNonNull(activitiesRequest)), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete activities request")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Activities request database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long activityRequestId) {
        Optional<HibernateRealEstateActivitiesRequest> optional = repository.findById(activityRequestId);
        HibernateRealEstateActivitiesRequest activitiesRequest = optional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        repository.delete(activitiesRequest);
        String delete = "Activities request with ID = " + activitiesRequest.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}
