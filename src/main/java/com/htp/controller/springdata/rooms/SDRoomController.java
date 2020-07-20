package com.htp.controller.springdata.rooms;

import com.htp.controller.hibernate.request.RoomSaveRequest;
import com.htp.dao.springdata.BuildingSDRepository;
import com.htp.dao.springdata.RoomSDRepository;
import com.htp.domain.hibernate.HibernateBuilding;
import com.htp.domain.hibernate.HibernateRoom;
import com.htp.exceptions.ResourceNotFoundException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/sd/rooms")
public class SDRoomController {

    private RoomSDRepository repository;
    private BuildingSDRepository buildingSDRepository;

    public SDRoomController(RoomSDRepository repository, BuildingSDRepository buildingSDRepository) {
        this.repository = repository;
        this.buildingSDRepository = buildingSDRepository;
    }


    @ApiOperation(value = "Finding all Rooms")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Rooms"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<List<HibernateRoom>> findAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Finding all Rooms in Building by buildingId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Role"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buildingId", value = "BuildingId database ID", example = "1", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<HibernateRoom>> findAllRoomsInBuilding(@PathVariable("buildingId") Long buildingId) {
        List<HibernateRoom> rooms = repository.findAllRoomByBuildingId(buildingId);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }


    @ApiOperation(value = "Finding Room by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Room"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Role database id", example = "1", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateRoom> findById(@PathVariable("id") Long roomId) {
        Optional<HibernateRoom> roomOptional = repository.findById(roomId);
        HibernateRoom room = roomOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        return new ResponseEntity<>(room, HttpStatus.OK);
    }


    @ApiOperation(value = "Create new Room")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation Room"),
            @ApiResponse(code = 422, message = "Failed Room creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header")
//    })
    @PostMapping
    public ResponseEntity<HibernateRoom> create(@Valid @RequestBody RoomSaveRequest request) {
        Optional<HibernateBuilding> buildingOptional = buildingSDRepository.findById(request.getBuildingId());
        HibernateBuilding building = buildingOptional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        HibernateRoom room = HibernateRoom.builder()
                .building(building)
                .roomArea(request.getRoomArea())
                .description(request.getDescription())
                .build();
        return new ResponseEntity<>(repository.save(room), HttpStatus.OK);
    }


    @ApiOperation(value = "Update Room")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Room update"),
            @ApiResponse(code = 400, message = "Invalid Room ID supplied"),
            @ApiResponse(code = 404, message = "Room was not found"),
            @ApiResponse(code = 422, message = "Failed Room creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Room database ID (buildingId not use)",
                    example = "8", required = true, dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateRoom> update(@Valid @PathVariable("id") Long roomId,
                                                @RequestBody RoomSaveRequest request) {
        Optional<HibernateRoom> roomOptional = repository.findById(roomId);
        HibernateRoom room = roomOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        room.setRoomArea(request.getRoomArea());
        room.setDescription(request.getDescription());
        return new ResponseEntity<>(repository.save(room), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Room")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Room database ID", example = "100",required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Long roomID) {
        Optional<HibernateRoom> roomOptional = repository.findById(roomID);
        HibernateRoom room = roomOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        repository.delete(room);
        String delete = "Room with ID = " + roomID + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
