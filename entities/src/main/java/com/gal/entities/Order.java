package com.gal.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gal.entities.enums.LocationType;
import com.gal.entities.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order {
    int id;
    @Min(1)
    int clientId;
    int orderGroupId;
    @NotNull
    OrderStatus status;
    @NotNull
    LocalDateTime dateTime;
    @Min(1)
    LocationType locationType;
    @NotNull
    String city;
    @NotNull
    String address;
    List<Payment> payments;
    //@Size(min=1)
    List<OrderItem> orderItems;
    List<Tag> tags;
    //add picture in the future
}
