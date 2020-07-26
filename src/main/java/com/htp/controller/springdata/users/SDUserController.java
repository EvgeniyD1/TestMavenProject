package com.htp.controller.springdata.users;

import com.htp.domain.hibernate.HibernateUser;
import com.htp.exceptions.ResourceNotFoundException;
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
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/sd/users")
public class SDUserController {

    private final UserSDService service;
    private final ConversionService conversionService;

    public SDUserController(UserSDService service, ConversionService conversionService) {
        this.service = service;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "Find all users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading users"),
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
    public ResponseEntity<Page<HibernateUser>> findAll(@ApiIgnore Pageable pageable) {
        Page<HibernateUser> users = service.findAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @ApiOperation(value = "Find user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User database id", example = "2", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateUser> findById(@PathVariable("id") Long userId) {
        Optional<HibernateUser> user = service.findById(userId);
        HibernateUser hibernateUser = user.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(hibernateUser, HttpStatus.OK);
    }


    @ApiOperation(value = "Find user by login")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "login", value = "Search query - Login", example = "ED",
                    required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/searchByLogin")
    public ResponseEntity<HibernateUser> findByLogin(@RequestParam("login") String query) {
        Optional<HibernateUser> byLogin = service.findByLogin(query);
        HibernateUser hibernateUser = byLogin.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(hibernateUser, HttpStatus.OK);
    }


    @ApiOperation(value = "Create user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation user"),
            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    public ResponseEntity<HibernateUser> create(@Valid @RequestBody UserSDSaveRequest request) {
        HibernateUser user = conversionService.convert(request, HibernateUser.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(user)), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Update user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 422, message = "Failed validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "User database id", example = "0" ,required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateUser> updateUser(@PathVariable("id") Long userId,
                                                    @Valid @RequestBody UserSDUpdateRequest request) {
        Optional<HibernateUser> byId = service.findById(userId);
        HibernateUser found = byId.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        request.setId(found.getId());
        HibernateUser user = conversionService.convert(request,HibernateUser.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(user)), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "User database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        Optional<HibernateUser> byId = service.findById(userId);
        HibernateUser user = byId.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        service.delete(user);
        String delete = "User with ID = " + user.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
