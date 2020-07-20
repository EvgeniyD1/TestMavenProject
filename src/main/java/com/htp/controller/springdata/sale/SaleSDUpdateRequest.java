package com.htp.controller.springdata.sale;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Rent object creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SaleSDUpdateRequest {

    @NotNull
    @ApiModelProperty(required = true, dataType = "string", example = "10000", notes = "RUB")
    private Long price;

    @NotNull
    @Size(max = 20)
    @ApiModelProperty(required = true, dataType = "string", example = "RUB", notes = "currency")
    private String currency;
}
