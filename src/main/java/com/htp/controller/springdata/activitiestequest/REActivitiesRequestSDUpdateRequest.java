package com.htp.controller.springdata.activitiestequest;

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
@ApiModel(description = "Real estate activities request update model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class REActivitiesRequestSDUpdateRequest extends REActivitiesRequestSDSaveRequest{

    @JsonIgnore
    @ApiModelProperty(dataType = "long", notes = "Real estate activities request database id", required = true)
    private Long id;

}
