package com.gal.dto.request.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDtoRequest {
    int id;
    int orderId;
    int serviceTypeId;
    double price;
    String description;
    //add picture in the future
}
