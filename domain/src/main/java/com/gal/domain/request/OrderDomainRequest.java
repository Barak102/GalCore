package com.gal.domain.request;

import com.gal.domain.common.base.DomainRequest;
import com.gal.entities.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDomainRequest extends DomainRequest {
    Order order;
}
