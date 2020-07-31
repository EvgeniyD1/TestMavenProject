package com.htp.controller.springdata.chat;

import com.htp.domain.HibernateChat;
import com.htp.domain.HibernateUser;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.service.chat.ChatSDService;
import com.htp.service.users.UserSDService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/sd/chat")
public class SDChatController {

    private final ChatSDService service;
    private final UserSDService userSDService;
    private final ConversionService conversionService;

    public SDChatController(ChatSDService service,
                            UserSDService userSDService,
                            ConversionService conversionService) {
        this.service = service;
        this.userSDService = userSDService;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "Search chat message by roomId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading chat message"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "Search query - roomId", example = "0",
                    required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/searchByRoom")
    public ResponseEntity<List<HibernateChat>> searchByRoom(@RequestParam("roomId") Long roomId) {
        List<HibernateChat> byRoomId = service.findByRoomId(roomId);
        return new ResponseEntity<>(byRoomId, HttpStatus.OK);
    }

    @ApiOperation(value = "Create chat message")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation chat message"),
            @ApiResponse(code = 422, message = "Failed chat message properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header")
    })
    @PostMapping
    public ResponseEntity<HibernateChat> createMessage(@Valid @RequestBody ChatSDSaveRequest request,
                                                       @ApiIgnore Principal principal) {
        Optional<HibernateUser> userOptional = userSDService.findByLogin(principal.getName());
        HibernateUser user = userOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        request.setUserId(user.getId());
        HibernateChat chat = conversionService.convert(request, HibernateChat.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(chat)), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Update chat message")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful chat message update"),
            @ApiResponse(code = 400, message = "Invalid chat message ID supplied"),
            @ApiResponse(code = 422, message = "Failed validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Chat message database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateChat> updateMessage(@PathVariable("id") Long messageId,
            @Valid @RequestBody ChatSDUpdateRequest request) {
        Optional<HibernateChat> optional = service.findById(messageId);
        HibernateChat found = optional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        request.setId(found.getId());
        HibernateChat chat = conversionService.convert(request, HibernateChat.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(chat)), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete chat message")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Chat message database id", example = "0", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("id") Long messageId) {
        Optional<HibernateChat> optional = service.findById(messageId);
        HibernateChat chat = optional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        service.delete(chat);
        String delete = "Activities request with ID = " + chat.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
