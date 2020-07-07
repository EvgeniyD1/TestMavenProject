package com.htp.controller.auth;

import com.htp.controller.request.AuthRequest;
import com.htp.controller.request.UserRequest;
import com.htp.controller.response.AuthResponse;
import com.htp.domain.Role;
import com.htp.domain.Roles;
import com.htp.domain.User;
import com.htp.security.util.TokenUtils;
import com.htp.service.RoleService;
import com.htp.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

@RestController
public class AuthController {

    private TokenUtils tokenUtils;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private UserService userService;
    private RoleService roleService;

    public AuthController(TokenUtils tokenUtils,
                          AuthenticationManager authenticationManager,
                          @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                          UserService userService,
                          RoleService roleService) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @ApiOperation(value = "Login user by username and password")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful login user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new ResponseEntity<>(
                AuthResponse
                        .builder()
                        .login(authRequest.getUsername())
                        .jwtToken(tokenUtils.generateToken(userDetailsService
                                .loadUserByUsername(authRequest.getUsername())))
                        .build(), HttpStatus.OK);
    }


    @ApiOperation(value = "Create user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation user"),
            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping("/registration")
    public ResponseEntity<User> create(@Valid @RequestBody UserRequest request) {
        User userForCreate = new User();
        userForCreate.setUsername(request.getUsername());
        userForCreate.setSurname(request.getSurname());
        userForCreate.setPatronymic(request.getPatronymic());
        userForCreate.setPhoneNumber(request.getPhoneNumber());
        userForCreate.setLogin(request.getLogin());
        userForCreate.setPassword(request.getPassword());
        userForCreate.setCreated(new Timestamp(new Date().getTime()));
        userForCreate.setChanged(new Timestamp(new Date().getTime()));
        userForCreate.setBirthDate(request.getBirthDate());
        userForCreate.setBlocked(request.isBlocked());
        userForCreate.setMail(request.getMail());
        userForCreate.setCountryLocation(request.getCountryLocation());

        User saveUser = userService.save(userForCreate);
        Role role = new Role();
        role.setRoleName(Roles.ROLE_USER.name());
        role.setUserId(saveUser.getId());
        Role saveRole = roleService.save(role);
        saveUser.setRole(saveRole);

        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }
}
