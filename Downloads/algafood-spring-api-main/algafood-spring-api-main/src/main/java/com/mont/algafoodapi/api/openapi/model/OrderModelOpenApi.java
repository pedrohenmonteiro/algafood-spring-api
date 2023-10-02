package com.mont.algafoodapi.api.openapi.model;

import com.mont.algafoodapi.api.model.OrderDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "OrderModel")
public class OrderModelOpenApi extends PagedModelOpenApi<OrderDto>{
    
}
