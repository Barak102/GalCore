package com.gal.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    int id;
    @Min(1)
    int orderId;
    @NotNull
    ServiceType serviceType;
    double price;
    String description;
}
