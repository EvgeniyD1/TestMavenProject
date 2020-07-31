package com.htp.controller.springdata.users;

import com.htp.dao.criteria.SpecificationBuilder;
import com.htp.domain.HibernateUser;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.service.users.UserSDService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    @ApiOperation(value = "Search Users by criteria")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Users"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "Search query - (key)(<:>)(value),('?)(key)(<:>)(value)",
                    example = "username:User", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/searchCriteria")
    public ResponseEntity<List<HibernateUser>> search(@RequestParam("search") String search) {
        SpecificationBuilder<HibernateUser> builder = new SpecificationBuilder<>();
        Pattern pattern = Pattern.compile("('?)(\\w+?)([:<>])(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
        }
        Specification<HibernateUser> spec = builder.build();
        List<HibernateUser> buildings = service.criteriaSpecification(spec);
        return new ResponseEntity<>(buildings, HttpStatus.OK);
    }


//    @ApiOperation(value = "Create user")
//    @ApiResponses({
//            @ApiResponse(code = 201, message = "Successful creation user"),
//            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
//            @ApiResponse(code = 500, message = "Server error, something wrong")
//    })
//    @PostMapping
//    public ResponseEntity<HibernateUser> create(@Valid @RequestBody UserSDSaveRequest request) {
//        HibernateUser user = conversionService.convert(request, HibernateUser.class);
//        return new ResponseEntity<>(service.save(Objects.requireNonNull(user)), HttpStatus.CREATED);
//    }


    @ApiOperation(value = "Update user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 422, message = "Failed validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
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
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
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