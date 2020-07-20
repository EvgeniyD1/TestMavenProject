package com.htp.controller.springdata.rent;

import com.htp.dao.springdata.RentSDRepository;
import com.htp.domain.hibernate.HibernateRent;
import com.htp.exceptions.ResourceNotFoundException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/sd/rent")
public class SDRentController {

    private RentSDRepository repository;

    public SDRentController(RentSDRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "Finding all Rent objects")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Rent objects"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<List<RentSDResponse>> findAll() {
        List<HibernateRent> rent = repository.findAll();
        List<RentSDResponse> responses = new ArrayList<>();
        for (HibernateRent hibernateRent : rent) {
            RentSDResponse response = RentSDResponse.builder()
                    .rentId(hibernateRent.getId())
                    .userLogin(hibernateRent.getUser().getLogin())
                    .buildingId(hibernateRent.getBuilding().getId())
                    .price(hibernateRent.getPrice())
                    .currency(hibernateRent.getCurrency())
                    .build();
            responses.add(response);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }


    @ApiOperation(value = "Finding Rent object by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Rent object"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Rent object database id", example = "1", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateRent> findById(@PathVariable("id") Long roleId) {
        Optional<HibernateRent> rentOptional = repository.findById(roleId);
        HibernateRent rent = rentOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(rent, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Rent object")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Rent object update"),
            @ApiResponse(code = 400, message = "Invalid Rent object ID supplied"),
            @ApiResponse(code = 422, message = "Failed validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Rent object database id", example = "8", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateRent> update(@Valid @PathVariable("id") Long roleId,
                                                @RequestBody RentSDUpdateRequest request) {
        Optional<HibernateRent> byId = repository.findById(roleId);
        HibernateRent rent = byId.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        rent.setPrice(request.getPrice());
        rent.setCurrency(request.getCurrency());
        return new ResponseEntity<>(repository.save(rent), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Rent object")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete Rent object"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Sale database id", example = "100", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long rentId) {
        Optional<HibernateRent> rentOptional = repository.findById(rentId);
        HibernateRent rent = rentOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        repository.delete(rent);
        String delete = "Rent object with ID = " + rent.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
