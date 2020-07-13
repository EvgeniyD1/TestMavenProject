package com.htp.controller.hibernate.controller;

import com.htp.controller.hibernate.request.RoomSaveRequest;
import com.htp.domain.hibernate.HibernateRoom;
import com.htp.service.hibernate.HibernateBuildingService;
import com.htp.service.hibernate.HibernateRoomService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@Transactional
@RequestMapping("/hibernateRooms")
public class HibernateRoomController {

    private HibernateRoomService hibernateRoomService;
    private HibernateBuildingService  hibernateBuildingService;

    public HibernateRoomController(HibernateRoomService hibernateRoomService,
                                   HibernateBuildingService hibernateBuildingService) {
        this.hibernateRoomService = hibernateRoomService;
        this.hibernateBuildingService = hibernateBuildingService;
    }

    @ApiOperation(value = "Finding all Rooms")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Rooms"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header")
//    })
    @GetMapping
    public ResponseEntity<List<HibernateRoom>> findAll() {
        return new ResponseEntity<>(hibernateRoomService.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Finding all Rooms in Building by buildingId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Role"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "buildingId", value = "BuildingId database ID", example = "1", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<HibernateRoom>> findAllRoomsInBuilding(@PathVariable("buildingId") Long buildingId) {
        List<HibernateRoom> allRoomsInBuilding = hibernateRoomService.findAllRoomsInBuilding(buildingId);
        if (allRoomsInBuilding.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Rooms in Building with id = " + buildingId +
                    " not found");
        }
        return new ResponseEntity<>(allRoomsInBuilding, HttpStatus.OK);
    }


    @ApiOperation(value = "Finding Room by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading Room"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Role database id", example = "1", required = true,
                    dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateRoom> findById(@PathVariable("id") Long roomId) {
        HibernateRoom room = hibernateRoomService.findOne(roomId);
        if (room == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Room with id = " + roomId + " not found");
        }
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
        HibernateRoom room = HibernateRoom.builder()
                .building(hibernateBuildingService.findOne(request.getBuildingId()))
                .roomArea(request.getRoomArea())
                .description(request.getDescription())
                .build();
        return new ResponseEntity<>(hibernateRoomService.save(room), HttpStatus.OK);
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
            @ApiImplicitParam(name = "id", value = "Room database ID", example = "8", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateRoom> update(@Valid @PathVariable("id") Long roomId,
                                                @RequestBody RoomSaveRequest request) {
        HibernateRoom room = hibernateRoomService.findOne(roomId);
        room.setRoomArea(request.getRoomArea());
        room.setDescription(request.getDescription());
        return new ResponseEntity<>(hibernateRoomService.update(room), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Room")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
//                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Room database ID", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long roomID) {
        HibernateRoom roomForDelete = hibernateRoomService.findOne(roomID);
        hibernateRoomService.delete(roomForDelete);
        String delete = "Room with ID = " + roomID + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
