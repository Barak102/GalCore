package com.gal.domain.response;

import com.gal.domain.common.base.DomainResponse;
import com.gal.entities.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDomainResponse extends DomainResponse {
    Order order;
}
