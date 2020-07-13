package com.htp.controller.hibernate.controller;

import com.htp.controller.hibernate.request.HibernateRoleRequest;
import com.htp.domain.hibernate.HibernateRole;
import com.htp.service.hibernate.HibernateRoleService;
import com.htp.service.hibernate.HibernateUserService;
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
@RequestMapping("/hibernateRoles")
public class HibernateRoleController {

    private final HibernateRoleService hibernateRoleService;
    private final HibernateUserService hibernateUserService;

    public HibernateRoleController(HibernateRoleService hibernateRoleService,
                                   HibernateUserService hibernateUserService) {
        this.hibernateRoleService = hibernateRoleService;
        this.hibernateUserService = hibernateUserService;
    }

    @ApiOperation(value = "Finding all Roles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Roles"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header")
//    })
    @GetMapping
    public ResponseEntity<List<HibernateRole>> findAll() {
        return new ResponseEntity<>(hibernateRoleService.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Finding Role by roleId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Role"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Role database id", example = "1", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateRole> findById(@PathVariable("id") Long roleId) {
        HibernateRole role = hibernateRoleService.findOne(roleId);
        if (role == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Role with id = " + roleId + " not found");
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }


    @ApiOperation(value = "Finding all User Roles by userId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Roles"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "userId", value = "User database id", example = "2", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HibernateRole>> findAllUserRoles(@PathVariable("userId") Long userId) {
        List<HibernateRole> roles = hibernateRoleService.findAllUserRoles(userId);
        if (roles.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Roles with userId = " + userId + " not found");
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


    @ApiOperation(value = "Create new Role")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation Role"),
            @ApiResponse(code = 422, message = "Failed Role creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header")
//    })
    @PostMapping
    public ResponseEntity<HibernateRole> create(@Valid @RequestBody HibernateRoleRequest request) {
        HibernateRole role = HibernateRole.builder()
                .roleName(request.getRoleName())
                .user(hibernateUserService.findOne(request.getUserId()))
                .build();
        return new ResponseEntity<>(hibernateRoleService.save(role), HttpStatus.OK);
    }


    @ApiOperation(value = "Update Role")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Role update"),
            @ApiResponse(code = 400, message = "Invalid Role ID supplied"),
            @ApiResponse(code = 404, message = "Role was not found"),
            @ApiResponse(code = 422, message = "Failed Role creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Role database id", example = "8", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateRole> update(@Valid @PathVariable("id") Long roleId,
                                                @RequestBody HibernateRoleRequest request) {
        HibernateRole role = hibernateRoleService.findOne(roleId);
        role.setRoleName(request.getRoleName());
        return new ResponseEntity<>(hibernateRoleService.update(role), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Role")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Role database id", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long roleId) {
        HibernateRole roleForDelete = hibernateRoleService.findOne(roleId);
        hibernateRoleService.delete(roleForDelete);
        String delete = "Role with ID = " + roleId + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
