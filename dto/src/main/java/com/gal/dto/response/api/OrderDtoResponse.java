package com.gal.dto.response.api;

import java.time.LocalDateTime;
import java.util.List;

import com.gal.entities.enums.LocationType;
import com.gal.entities.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDtoResponse {
    int id;
    int clientId;
    int orderGroupId;
    OrderStatus status;
    LocalDateTime dateTime;
    LocationType locationType;
    String address;
    String city;
    List<OrderItemDtoResponse> orderItems;
    List<PaymentDtoResponse> payments;
    List<TagDtoResponse> tags;
}
