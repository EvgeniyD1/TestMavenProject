package com.htp.controller.springdata.chat;

import com.htp.domain.Chat;
import com.htp.domain.User;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.service.chat.ChatService;
import com.htp.service.users.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/chat")
public class ChatController {

    private final ChatService service;
    private final UserService userService;
    private final ConversionService conversionService;

    public ChatController(ChatService service,
                          UserService userService,
                          ConversionService conversionService) {
        this.service = service;
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "Search chat message by roomId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading chat message"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "Search query - roomId", example = "1",
                    required = true, dataType = "long", paramType = "query")
    })
    @GetMapping("/searchByRoom")
    public ResponseEntity<List<Chat>> searchByRoom(@RequestParam("roomId") Long roomId) {
        List<Chat> byRoomId = service.findByRoomId(roomId);
        return new ResponseEntity<>(byRoomId, HttpStatus.OK);
    }

    @ApiOperation(value = "Create message")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation message"),
            @ApiResponse(code = 422, message = "Failed chat message properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header")
    })
    @PostMapping
    public ResponseEntity<Chat> createMessage(@Valid @RequestBody ChatSaveRequest request,
                                              @ApiIgnore Principal principal) {
        Optional<User> userOptional = userService.findByLogin(principal.getName());
        User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        request.setUserId(user.getId());
        Chat chat = conversionService.convert(request, Chat.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(chat)), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Update message")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful message update"),
            @ApiResponse(code = 400, message = "Invalid chat message ID supplied"),
            @ApiResponse(code = 422, message = "Failed validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Chat message database id", example = "2", required = true,
                    dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Chat> updateMessage(@PathVariable("id") Long messageId,
                                              @Valid @RequestBody ChatUpdateRequest request) {
        Optional<Chat> optional = service.findById(messageId);
        Chat found = optional.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        request.setId(found.getId());
        Chat chat = conversionService.convert(request, Chat.class);
        return new ResponseEntity<>(service.save(Objects.requireNonNull(chat)), HttpStatus.OK);
    }


    @ApiOperation(value = "Delete message")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string",
                    paramType = "header"),
            @ApiImplicitParam(name = "id", value = "Chat message database id", example = "3", required = true,
                    dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("id") Long messageId) {
        Optional<Chat> optional = service.findById(messageId);
        Chat chat = optional.orElseThrow(() ->
                new ResourceNotFoundException("Resource Not Found"));
        service.delete(chat);
        String delete = "Message with ID = " + chat.getId() + " deleted";
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}
