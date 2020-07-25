package com.htp.controller.springdata.activitiestequest;

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
@ApiModel(description = "Real estate activities request creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class REActivitiesRequestSDSaveRequest {

    @ApiModelProperty(required = true, example = "26", dataType = "long", notes = "Real estate activities id")
    private Long realEstateActivityId;

    @Size(max = 300)
    @JsonIgnore
    @ApiModelProperty(required = true, example = "http://localhost:8080/sd/users/2",
            dataType = "string", notes = "user link")
    private String userLink;

    @NotNull
    @NotEmpty
    @Size(max = 300)
    @ApiModelProperty(required = true, example = "rent", dataType = "string", notes = "message")
    private String message;
}
