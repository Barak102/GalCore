package com.gal.dto.response.api;

import com.gal.entities.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDtoResponse {
    int id;
    int orderId;
    double amount;
    PaymentMethod paymentMethod;
}
