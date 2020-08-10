package com.htp.controller.springdata.activitiestequest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
@ApiModel(description = "Real estate activities request create model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ActivitiesRequestSaveRequest extends ActivitiesRequestRequest {

    @ApiModelProperty(required = true, example = "26", dataType = "long", notes = "Real estate activities id")
    private Long realEstateActivityId;

}
