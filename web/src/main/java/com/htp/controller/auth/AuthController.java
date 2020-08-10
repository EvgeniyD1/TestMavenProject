package com.htp.controller.auth;

import com.htp.controller.springdata.users.UserSaveRequest;
import com.htp.domain.User;
import com.htp.security.util.TokenUtils;
import com.htp.service.users.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
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
import java.util.Objects;

@RestController
public class AuthController {

    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService service;
    private final ConversionService conversionService;

    public AuthController(TokenUtils tokenUtils,
                          AuthenticationManager authenticationManager,
                          @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                          UserService service,
                          ConversionService conversionService) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.service = service;
        this.conversionService = conversionService;
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
    public ResponseEntity<User> create(@Valid @RequestBody UserSaveRequest request) {
        User user = conversionService.convert(request, User.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(user)), HttpStatus.CREATED);
    }
}
