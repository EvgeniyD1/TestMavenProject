package com.htp.controller.hibernate.controller;

import com.htp.controller.hibernate.request.UserSaveRequest;
import com.htp.controller.hibernate.request.UserUpdateRequest;
import com.htp.domain.hibernate.HibernateRole;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.service.hibernate.HibernateUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@Transactional
@RequestMapping("/hibernateUsers")
public class HibernateUserController {

    private HibernateUserService hibernateUserService;

    public HibernateUserController(HibernateUserService hibernateUserService) {
        this.hibernateUserService = hibernateUserService;
    }

    //+
    @ApiOperation(value = "Finding all users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading users"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header")
//    })
    @GetMapping
    public ResponseEntity<List<HibernateUser>> findAll() {
        return new ResponseEntity<>(hibernateUserService.findAll(), HttpStatus.OK);
    }

    //+
    @ApiOperation(value = "Finding user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "User database id", example = "2", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateUser> findById(@PathVariable("id") Long userId) {
        HibernateUser user = hibernateUserService.findOne(userId);
        if (user == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User with id = " + userId + " not found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //+
    @ApiOperation(value = "Search users by username, surname and patronymic")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "surname", value = "Search query - surname", example = "Dolgiy",
                    required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "Search query - username, if empty then 0",
                    example = "Evgeniy", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "patronymic", value = "Search query - patronymic, if empty then 0",
                    example = "Pavlovich", required = false, dataType = "string", paramType = "query")
    })
    @GetMapping("/search")
    public ResponseEntity<List<HibernateUser>> searchUser(@RequestParam("username") String username,
                                                          @RequestParam("surname") String surname,
                                                          @RequestParam("patronymic") String patronymic) {
        List<HibernateUser> searchResult = hibernateUserService.searchUserByFullName(
                username, surname, patronymic);
        if (searchResult.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User with surname '" + surname
                    + "' , username '" + username + "' and patronymic '" + patronymic + "' not found");
        }
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

    //+
    @ApiOperation(value = "Search user by login")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "login", value = "Search query - Login", example = "ED",
                    required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/searchByLogin")
    public ResponseEntity<HibernateUser> findByLogin(@RequestParam("login") String query) {
        HibernateUser byLogin = hibernateUserService.findByLogin(query);
        return new ResponseEntity<>(byLogin, HttpStatus.OK);
    }

    //+
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
    public ResponseEntity<HibernateUser> create(@Valid @RequestBody UserSaveRequest request) {
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
        return new ResponseEntity<>(hibernateUserService.save(user), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Update user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header")
//    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateUser> updateUser(@Valid @PathVariable("id") Long userId,
                                                    @RequestBody UserUpdateRequest request) {
        HibernateUser user = hibernateUserService.findOne(userId);
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
        return new ResponseEntity<>(hibernateUserService.update(user), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete user")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header")
//    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        HibernateUser userForDelete = hibernateUserService.findOne(userId);
        hibernateUserService.delete(userForDelete);
        String delete = "User with ID = " + userId + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
