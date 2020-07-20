package com.htp.controller.springdata.sale;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Rent object response model")
public class SaleSDResponse {

    private Long rentId;

    private String userLogin;

    private Long buildingId;

    private Long price;

    private String currency;
}
