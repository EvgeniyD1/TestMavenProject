package com.htp.controller.springdata.chat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Chat message creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ChatRequest {

    @JsonIgnore
    @ApiModelProperty(required = true, example = "0", dataType = "long", notes = "User id")
    private Long userId;

    @NotNull
    @NotEmpty
    @Size(max = 1000)
    @ApiModelProperty(required = true, example = "message", dataType = "string", notes = "message")
    private String message;
}
