package com.htp.controller.hibernate.request;

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
@ApiModel(description = "Room creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RoomSaveRequest {

    @ApiModelProperty(required = true, dataType = "long", notes = "Building ID")
    private Long buildingId;

    @ApiModelProperty(required = true, dataType = "integer", notes = "Room area")
    private Integer roomArea;

    @ApiModelProperty(dataType = "string", notes = "Room description")
    private String  description;

}
