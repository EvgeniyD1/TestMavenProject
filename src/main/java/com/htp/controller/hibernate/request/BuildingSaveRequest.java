package com.htp.controller.hibernate.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Building creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BuildingSaveRequest {

    @NotNull
    @NotEmpty
    @Size(max = 50)
    @ApiModelProperty(required = true, dataType = "string", notes = "type of building")
    private String type;

    @ApiModelProperty(dataType = "integer", notes = "land area")
    private int landArea;

    @ApiModelProperty(required = true, dataType = "integer", notes = "rooms count")
    private int roomsCount;

    @ApiModelProperty(required = true, dataType = "integer", notes = "total rooms area")
    private int totalRoomsArea;

    @ApiModelProperty(dataType = "integer", notes = "living area")
    private int livingArea;

    @ApiModelProperty(dataType = "integer", notes = "kitchen area")
    private int kitchenArea;

    @ApiModelProperty(dataType = "integer", notes = "building floors")
    private int buildingFloors;

    @ApiModelProperty(dataType = "integer", notes = "floor")
    private int floor;

    @ApiModelProperty(dataType = "integer", notes = "building year")
    private int buildingYear;

    @ApiModelProperty(dataType = "boolean", notes = "is repairs")
    private boolean isRepairs;

    @ApiModelProperty(dataType = "boolean", notes = "is garage")
    private boolean isGarage;

    @ApiModelProperty(dataType = "boolean", notes = "is barn")
    private boolean isBarn;

    @ApiModelProperty(dataType = "boolean", notes = "is bath")
    private boolean isBath;

    @Size(max = 3000)
    @ApiModelProperty(dataType = "string", notes = "building description")
    private String description;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "country  location")
    private String countryLocation;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "region  location")
    private String regionLocation;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "town  location")
    private String townLocation;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "street  location")
    private String streetLocation;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "building  location")
    private String buildingLocation;

    @Size(max = 100)
    @ApiModelProperty(dataType = "string", notes = "room  location")
    private String roomLocation;

}
