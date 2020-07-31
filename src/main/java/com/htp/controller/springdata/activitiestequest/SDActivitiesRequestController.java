package com.htp.controller.springdata.activitiestequest;

import com.htp.controller.springdata.Link;
import com.htp.domain.HibernateActivitiesRequest;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.service.activityrequest.ActivityRequestSDService;
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
public class SDActivitiesRequestController {

    private final ActivityRequestSDService service;
    private final ConversionService conversionService;

    public SDActivitiesRequestController(ActivityRequestSDService service,
                                         ConversionService conversionService) {
        this.service = service;
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
    public ResponseEntity<HibernateActivitiesRequest> findById(@PathVariable("id") Long activitiesReqId) {
        Optional<HibernateActivitiesRequest> optional = service.findById(activitiesReqId);
        HibernateActivitiesRequest request = optional
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
    public ResponseEntity<List<HibernateActivitiesRequest>> findAllActivitiesRequestByActivitiesId(
            @PathVariable("activitiesId") Long activitiesId) {
        List<HibernateActivitiesRequest> ar = service.findAllActivitiesRequestByActivitiesId(activitiesId);
        return new ResponseEntity<>(ar, HttpStatus.OK);
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
    public ResponseEntity<HibernateActivitiesRequest> create(
            @Valid @RequestBody ActivitiesRequestSDSaveRequest request,
            @ApiIgnore Principal principal) {
        request.setUserLink(Link.URL + "/sd/users/searchByLogin?login=" + principal.getName());
        HibernateActivitiesRequest activitiesRequest = conversionService
                .convert(request, HibernateActivitiesRequest.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(activitiesRequest)), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Update activities request")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful activities request update"),
            @ApiResponse(code = 400, message = "Invalid activities request ID supplied"),
            @ApiResponse(code = 422, message = "Failed validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Activities request database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateActivitiesRequest> update(
            @PathVariable("id") Long activitiesRequestId,
            @Valid @RequestBody ActivitiesRequestSDUpdateRequest request) {
        Optional<HibernateActivitiesRequest> optional = service.findById(activitiesRequestId);
        HibernateActivitiesRequest found = optional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        request.setId(found.getId());
        HibernateActivitiesRequest activitiesRequest = conversionService.convert(request,
                HibernateActivitiesRequest.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(activitiesRequest)), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete activities request")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Activities request database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long activityRequestId) {
        Optional<HibernateActivitiesRequest> optional = service.findById(activityRequestId);
        HibernateActivitiesRequest activitiesRequest = optional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        service.delete(activitiesRequest);
        String delete = "Activities request with ID = " + activitiesRequest.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}
