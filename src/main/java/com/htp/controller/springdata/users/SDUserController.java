package com.htp.controller.springdata.users;

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
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/sd/users")
public class SDUserController {

    private UserSDRepository repository;

    public SDUserController(UserSDRepository repository) {
        this.repository = repository;
    }


    @ApiOperation(value = "Finding all users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading users"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<List<HibernateUser>> findAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Finding user by id")
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
        Optional<HibernateUser> user = repository.findById(userId);
        HibernateUser hibernateUser = user.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(hibernateUser, HttpStatus.OK);
    }


    @ApiOperation(value = "Search user by login")
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
        Optional<HibernateUser> byLogin = repository.findByLogin(query);
        HibernateUser hibernateUser = byLogin.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(hibernateUser, HttpStatus.OK);
    }


    @ApiOperation(value = "Create user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation user"),
            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header")
//    })
    @PostMapping
    public ResponseEntity<HibernateUser> create(@Valid @RequestBody UserSDSaveRequest request) {
        HibernateUser user = HibernateUser.builder()
                .username(request.getUsername())
                .surname(request.getSurname())
                .patronymic(request.getPatronymic())
                .phoneNumber(request.getPhoneNumber())
                .login(request.getLogin())
                .password(request.getPassword())
                .created(new Timestamp(new Date().getTime()))
                .changed(new Timestamp(new Date().getTime()))
                .blocked(false)
                .birthDate(request.getBirthDate())
                .mail(request.getMail())
                .countryLocation(request.getCountryLocation())
                .build();
        HibernateRole role = HibernateRole.builder()
                .roleName("ROLE_USER")
                .user(user)
                .build();
        user.setRoles(Collections.singleton(role));
        return new ResponseEntity<>(repository.save(user), HttpStatus.CREATED);
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
            @ApiImplicitParam(name = "id", value = "User database id", example = "100" ,required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateUser> updateUser(@Valid @PathVariable("id") Long userId,
                                                    @RequestBody UserSDUpdateRequest request) {
        Optional<HibernateUser> byId = repository.findById(userId);
        HibernateUser user = byId.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        user.setUsername(request.getUsername());
        user.setSurname(request.getSurname());
        user.setPatronymic(request.getPatronymic());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setBirthDate(request.getBirthDate());
        user.setBlocked(request.isBlocked());
        user.setMail(request.getMail());
        user.setCountryLocation(request.getCountryLocation());
        return new ResponseEntity<>(repository.save(user), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "User database id", example = "100", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        Optional<HibernateUser> byId = repository.findById(userId);
        HibernateUser user = byId.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        repository.delete(user);
        String delete = "User with ID = " + user.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
