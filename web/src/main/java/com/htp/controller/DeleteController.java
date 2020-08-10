package com.htp.controller;

import com.htp.domain.Activities;
import com.htp.domain.ActivitiesRequest;
import com.htp.domain.Building;
import com.htp.domain.Role;
import com.htp.domain.User;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.service.activity.ActivityService;
import com.htp.service.activityrequest.ActivityRequestService;
import com.htp.service.building.BuildingService;
import com.htp.service.delete.DeleteService;
import com.htp.service.role.RoleService;
import com.htp.service.users.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/delete")
public class DeleteController {

    private final UserService userService;
    private final RoleService roleService;
    private final BuildingService buildingService;
    private final ActivityService activityService;
    private final ActivityRequestService activityRequestService;
    private final DeleteService deleteService;

    public DeleteController(UserService userService,
                            RoleService roleService,
                            BuildingService buildingService,
                            ActivityService activityService,
                            ActivityRequestService activityRequestService,
                            DeleteService deleteService) {
        this.userService = userService;
        this.roleService = roleService;
        this.buildingService = buildingService;
        this.activityService = activityService;
        this.activityRequestService = activityRequestService;
        this.deleteService = deleteService;
    }

    @ApiOperation(value = "Delete user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "User database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PatchMapping("/delete/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        Optional<User> byId = userService.findById(userId);
        User user = byId.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        deleteService.deleteUser(userId);
        String delete = "User with ID = " + user.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }


    @ApiOperation(value = "Restore user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful restore user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "User database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PatchMapping("/restore/user/{id}")
    public ResponseEntity<String> restoreUser(@PathVariable("id") Long userId) {
        Optional<User> byId = userService.findById(userId);
        User user = byId.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        deleteService.restoreUser(userId);
        String restore = "User with ID = " + user.getId() + " restored";
        return new ResponseEntity<>(restore, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Role")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Role database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PatchMapping("/delete/role/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long roleId) {
        Optional<Role> roleOptional = roleService.findById(roleId);
        Role role = roleOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        deleteService.deleteRole(roleId);
        String delete = "Role with ID = " + role.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }


    @ApiOperation(value = "Restore Role")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful restore user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Role database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PatchMapping("/restore/role/{id}")
    public ResponseEntity<String> restoreRole(@PathVariable("id") Long roleId) {
        Optional<Role> roleOptional = roleService.findById(roleId);
        Role role = roleOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        deleteService.restoreRole(roleId);
        String restore = "Role with ID = " + role.getId() + " restored";
        return new ResponseEntity<>(restore, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Building")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete building"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Building database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PatchMapping("/delete/building/{id}")
    public ResponseEntity<String> deleteBuilding(@PathVariable("id") Long buildingId) {
        Optional<Building> buildingOptional = buildingService.findById(buildingId);
        Building building = buildingOptional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        deleteService.deleteBuilding(buildingId);
        String delete = "Building with ID = " + building.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }


    @ApiOperation(value = "Restore Building")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful restore building"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Building database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PatchMapping("/restore/building/{id}")
    public ResponseEntity<String> restoreBuilding(@PathVariable("id") Long buildingId) {
        Optional<Building> buildingOptional = buildingService.findById(buildingId);
        Building building = buildingOptional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        deleteService.restoreBuilding(buildingId);
        String restore = "Building with ID = " + building.getId() + " restored";
        return new ResponseEntity<>(restore, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Activities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete activities"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Activities database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PatchMapping("/delete/activities/{id}")
    public ResponseEntity<String> deleteActivities(@PathVariable("id") Long activityId) {
        Optional<Activities> optional = activityService.findById(activityId);
        Activities activities = optional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        deleteService.deleteActivities(activityId);
        String delete = "Activities with ID = " + activities.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }


    @ApiOperation(value = "Restore Activities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful restore activities"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Activities database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PatchMapping("/restore/activities/{id}")
    public ResponseEntity<String> restoreActivities(@PathVariable("id") Long activityId) {
        Optional<Activities> optional = activityService.findById(activityId);
        Activities activities = optional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        deleteService.restoreActivities(activityId);
        String restore = "Activities with ID = " + activities.getId() + " restored";
        return new ResponseEntity<>(restore, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete activities request")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete activities request"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Activities request database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PatchMapping("/delete/activities_request/{id}")
    public ResponseEntity<String> deleteActivitiesRequest(@PathVariable("id") Long activityRequestId) {
        Optional<ActivitiesRequest> optional = activityRequestService.findById(activityRequestId);
        ActivitiesRequest activitiesRequest = optional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        deleteService.deleteActivitiesRequest(activityRequestId);
        String delete = "Activities request with ID = " + activitiesRequest.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete activities request")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful restore activities request"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Activities request database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PatchMapping("/restore/activities_request/{id}")
    public ResponseEntity<String> restoreActivitiesRequest(@PathVariable("id") Long activityRequestId) {
        Optional<ActivitiesRequest> optional = activityRequestService.findById(activityRequestId);
        ActivitiesRequest activitiesRequest = optional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        deleteService.restoreActivitiesRequest(activityRequestId);
        String restore = "Activities request with ID = " + activitiesRequest.getId() + " restored";
        return new ResponseEntity<>(restore, HttpStatus.OK);
    }
}
