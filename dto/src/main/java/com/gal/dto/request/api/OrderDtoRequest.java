package com.gal.dto.request.api;

import com.gal.entities.Order;
import com.gal.entities.enums.LocationType;
import com.gal.entities.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDtoRequest {
    int id;
    int clientId;
    int orderGroupId;
    OrderStatus status;
    LocalDateTime dateTime;
    LocationType locationType;
    String city;
    String address;
}
