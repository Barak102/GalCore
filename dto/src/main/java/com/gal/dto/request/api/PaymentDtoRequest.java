package com.gal.dto.request.api;

import com.gal.entities.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDtoRequest {
    int id;
    int orderId;
    double amount;
    PaymentMethod paymentMethod;
}
