package com.htp.controller.springdata.sale;

import com.htp.dao.springdata.SaleSDRepository;
import com.htp.domain.hibernate.HibernateSale;
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
@RequestMapping("/sd/sale")
public class SDSaleController {

    private SaleSDRepository repository;

    public SDSaleController(SaleSDRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "Finding all Sale objects")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Sale objects"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<List<SaleSDResponse>> findAll() {
        List<HibernateSale> sale = repository.findAll();
        List<SaleSDResponse> responses = new ArrayList<>();
        for (HibernateSale hibernateSale : sale) {
            SaleSDResponse response = SaleSDResponse.builder()
                    .rentId(hibernateSale.getId())
                    .userLogin(hibernateSale.getUser().getLogin())
                    .buildingId(hibernateSale.getBuilding().getId())
                    .price(hibernateSale.getPrice())
                    .currency(hibernateSale.getCurrency())
                    .build();
            responses.add(response);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }


    @ApiOperation(value = "Finding Sale object by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Sale object"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Sale object database id", example = "1", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateSale> findById(@PathVariable("id") Long roleId) {
        Optional<HibernateSale> saleOptional = repository.findById(roleId);
        HibernateSale rent = saleOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(rent, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Sale object")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Sale object update"),
            @ApiResponse(code = 400, message = "Invalid Sale object ID supplied"),
            @ApiResponse(code = 422, message = "Failed validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Sale object database id", example = "8", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateSale> update(@Valid @PathVariable("id") Long roleId,
                                                @RequestBody SaleSDUpdateRequest request) {
        Optional<HibernateSale> byId = repository.findById(roleId);
        HibernateSale sale = byId.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        sale.setPrice(request.getPrice());
        sale.setCurrency(request.getCurrency());
        return new ResponseEntity<>(repository.save(sale), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Sale object")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful delete Sale object"),
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
        Optional<HibernateSale> saleOptional = repository.findById(rentId);
        HibernateSale sale = saleOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        repository.delete(sale);
        String delete = "Sale object with ID = " + sale.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
