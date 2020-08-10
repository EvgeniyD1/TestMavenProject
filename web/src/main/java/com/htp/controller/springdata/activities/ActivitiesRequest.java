package com.htp.controller.springdata.activities;

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
@ApiModel(description = "Real estate activities creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ActivitiesRequest {

    @ApiModelProperty(required = true, example = "1000", dataType = "long", notes = "price")
    private Long price;

    @NotNull
    @NotEmpty
    @Size(max = 20)
    @ApiModelProperty(required = true, example = "RUB", dataType = "string", notes = "currency")
    private String currency;

//    @NotNull
//    @NotEmpty
    @Size(max = 300)
    @JsonIgnore
    @ApiModelProperty(required = true, example = "http://localhost:8080/sd/users/2",
            dataType = "string", notes = "user link")
    private String userLink;

//    @NotNull
//    @NotEmpty
    @Size(max = 300)
    @JsonIgnore
    @ApiModelProperty(required = true, example = "http://localhost:8080/sd/buildings/2",
            dataType = "string", notes = "building link")
    private String buildingLink;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, example = "rent", dataType = "string", notes = "type")
    private String type;
}
