package com.htp.controller.springdata.roles;

import com.htp.domain.Role;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.service.role.RoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/roles")
public class RoleController {

    private final RoleService service;
    private final ConversionService conversionService;

    public RoleController(RoleService service, ConversionService conversionService) {
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
    public ResponseEntity<Page<Role>> findAll(@ApiIgnore Pageable pageable) {
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
    public ResponseEntity<Role> findById(@PathVariable("id") Long roleId) {
        Optional<Role> optionalRole = service.findById(roleId);
        Role role = optionalRole.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(role, HttpStatus.OK);
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
    public ResponseEntity<List<Role>> findAllUserRoles(@PathVariable("userId") Long userId) {
        List<Role> roles = service.findAllRolesByUserId(userId);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


    @ApiOperation(value = "Create new Role")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation Role"),
            @ApiResponse(code = 422, message = "Failed Role creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header")
    })
    @PostMapping
    public ResponseEntity<Role> create(@Valid @RequestBody RoleSaveRequest request) {
        Role role = conversionService.convert(request, Role.class);
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
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Role database id (userId not use)", example = "2", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable("id") Long roleId,
                                       @Valid @RequestBody RoleUpdateRequest request) {
        Optional<Role> roleOptional = service.findById(roleId);
        Role found = roleOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        request.setId(found.getId());
        Role role = conversionService.convert(request, Role.class);
        return new ResponseEntity<>(service.save(role), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Role")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Role database id", example = "3", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long roleId) {
        Optional<Role> roleOptional = service.findById(roleId);
        Role role = roleOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        service.delete(role);
        String delete = "Role with ID = " + roleId + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}
