package com.htp.controller.springdata.roles;

import com.htp.dao.springdata.RoleSDRepository;
import com.htp.dao.springdata.UserSDRepository;
import com.htp.domain.hibernate.HibernateRole;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.exceptions.ResourceNotFoundException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/sd/roles")
public class SDRoleController {

    private RoleSDRepository repository;
    private UserSDRepository userSDRepository;

    public SDRoleController(RoleSDRepository repository, UserSDRepository userSDRepository) {
        this.repository = repository;
        this.userSDRepository = userSDRepository;
    }


    @ApiOperation(value = "Finding all Roles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Roles"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<List<HibernateRole>> findAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Finding Role by roleId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Role"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Role database id", example = "1", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateRole> findById(@PathVariable("id") Long roleId) {
        Optional<HibernateRole> role = repository.findById(roleId);
        HibernateRole hibernateRole = role.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(hibernateRole, HttpStatus.OK);
    }


    @ApiOperation(value = "Finding all User Roles by userId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Roles"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "User database id", example = "2", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HibernateRole>> findAllUserRoles(@PathVariable("userId") Long userId) {
        List<HibernateRole> roles = repository.findAllRolesByUserId(userId);
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
    public ResponseEntity<HibernateRole> create(@Valid @RequestBody RoleSDSaveRequest request) {
        Optional<HibernateUser> userOptional = userSDRepository.findById(request.getUserId());
        HibernateUser user = userOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        HibernateRole role = HibernateRole.builder()
                .roleName(request.getRoleName())
                .user(user)
                .build();
        return new ResponseEntity<>(repository.save(role), HttpStatus.OK);
    }


    @ApiOperation(value = "Update Role")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Role update"),
            @ApiResponse(code = 400, message = "Invalid Role ID supplied"),
            @ApiResponse(code = 422, message = "Failed validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Role database id (userId not use)", example = "8", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateRole> update(@Valid @PathVariable("id") Long roleId,
                                                @RequestBody RoleSDSaveRequest request) {
        Optional<HibernateRole> roleOptional = repository.findById(roleId);
        HibernateRole role = roleOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        role.setRoleName(request.getRoleName());
        return new ResponseEntity<>(repository.save(role), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Role")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Role database id", example = "100", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long roleId) {
        Optional<HibernateRole> roleOptional = repository.findById(roleId);
        HibernateRole role = roleOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        repository.delete(role);
        String delete = "Role with ID = " + roleId + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
