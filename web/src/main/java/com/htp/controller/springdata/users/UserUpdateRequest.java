package com.htp.controller.springdata.users;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(description = "User update model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserUpdateRequest extends UserSaveRequest {

    @JsonIgnore
    @ApiModelProperty(dataType = "long", notes = "user database id", required = true)
    private Long id;

    @ApiModelProperty(dataType = "boolean", notes = "user is blocked")
    private boolean isBlocked;

}
