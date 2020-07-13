package com.htp.controller.hibernate.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Role creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HibernateRoleRequest {


    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "role name")
    private String roleName;


    @ApiModelProperty(required = true, dataType = "long", notes = "user ID")
    private Long userId;
}
