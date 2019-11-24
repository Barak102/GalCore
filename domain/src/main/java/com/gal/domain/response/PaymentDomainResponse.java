package com.gal.domain.response;

import com.gal.domain.common.base.DomainResponse;
import com.gal.entities.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDomainResponse extends DomainResponse {
    Payment payment;
}
