package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "ID")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class IdRequest {

    @ApiModelProperty(required = true, dataType = "long", notes = "id")
    Long id;
}
