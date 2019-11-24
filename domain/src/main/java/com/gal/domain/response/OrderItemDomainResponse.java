package com.gal.domain.response;

import com.gal.domain.common.base.DomainResponse;
import com.gal.entities.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDomainResponse extends DomainResponse {
    OrderItem orderItem;
}
