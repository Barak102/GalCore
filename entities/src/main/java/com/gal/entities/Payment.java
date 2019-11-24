package com.gal.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gal.entities.enums.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Payment {
    int id;
    @Min(1)
    int orderId;
    double amount;
    @NotNull
    PaymentMethod paymentMethod;
}
