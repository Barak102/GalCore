package com.gal.dto.request.api;

import com.gal.entities.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusChangeDtoRequest {
    int orderId;
    OrderStatus status;
}
