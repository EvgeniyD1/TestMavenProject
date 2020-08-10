package com.htp.controller.springdata.buildings;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
@ApiModel(description = "Building creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BuildingRequest {

    @NotNull
    @NotEmpty
    @Size(max = 50)
    @ApiModelProperty(required = true, dataType = "string", example = "house", notes = "type of building")
    private String type;

    @ApiModelProperty(dataType = "integer", example = "100", notes = "land area")
    private int landArea;

    @ApiModelProperty(required = true, dataType = "integer", example = "3", notes = "rooms count")
    private int roomsCount;

    @ApiModelProperty(required = true, dataType = "integer", example = "80", notes = "total rooms area")
    private int totalRoomsArea;

    @ApiModelProperty(dataType = "integer", example = "50",notes = "living area")
    private int livingArea;

    @ApiModelProperty(dataType = "integer", example = "20", notes = "kitchen area")
    private int kitchenArea;

    @ApiModelProperty(dataType = "integer", example = "10", notes = "building floors")
    private int buildingFloors;

    @ApiModelProperty(dataType = "integer", example = "5", notes = "floor")
    private int floor;

    @ApiModelProperty(dataType = "integer", example = "1999", notes = "building year")
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
    @ApiModelProperty(dataType = "string", example = "cool house",  notes = "building description")
    private String description;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", example = "Belarus", notes = "country  location")
    private String countryLocation;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", example = "Minsk", notes = "region  location")
    private String regionLocation;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", example = "Minsk", notes = "town  location")
    private String townLocation;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", example = "Pushkin", notes = "street  location")
    private String streetLocation;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", example = "10", notes = "building  location")
    private String buildingLocation;

    @Size(max = 100)
    @ApiModelProperty(dataType = "string",  example = "10", notes = "room  location")
    private String roomLocation;

}
