package com.gal.dto.request.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagToOrderDtoRequest {
    int orderId;
    String [] names;
}
