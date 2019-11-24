package com.gal.domain.request;

import com.gal.domain.common.base.DomainRequest;
import com.gal.entities.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDomainRequest extends DomainRequest {
    Payment payment;
}
