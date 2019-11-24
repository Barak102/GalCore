package com.gal.dto.response.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDtoResponse {
    int id;
    int orderId;
    ServiceTypeDtoResponse serviceType;
    double price;
    String description;
}
