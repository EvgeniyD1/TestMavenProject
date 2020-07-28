package com.htp.controller.springdata.roles;

import com.htp.domain.HibernateRole;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.service.role.RoleSDService;
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
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/sd/roles")
public class SDRoleController {

    private final RoleSDService service;
    private final ConversionService conversionService;

    public SDRoleController(RoleSDService service, ConversionService conversionService) {
        this.service = service;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "Finding all Roles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Roles"),
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
    public ResponseEntity<Page<HibernateRole>> findAll(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
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
        Optional<HibernateRole> role = service.findById(roleId);
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
        List<HibernateRole> roles = service.findAllRolesByUserId(userId);
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
        HibernateRole role = conversionService.convert(request, HibernateRole.class);
        return new ResponseEntity<>(service.save(role), HttpStatus.OK);
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
            @ApiImplicitParam(name = "id", value = "Role database id (userId not use)", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateRole> update(@PathVariable("id") Long roleId,
                                                @Valid @RequestBody RoleSDUpdateRequest request) {
        Optional<HibernateRole> roleOptional = service.findById(roleId);
        HibernateRole found = roleOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        request.setId(found.getId());
        HibernateRole role = conversionService.convert(request, HibernateRole.class);
        return new ResponseEntity<>(service.save(role), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Role")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Role database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long roleId) {
        Optional<HibernateRole> roleOptional = service.findById(roleId);
        HibernateRole role = roleOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        service.delete(role);
        String delete = "Role with ID = " + roleId + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
