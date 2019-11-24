package com.gal.domain.request;

import com.gal.domain.common.base.DomainRequest;
import com.gal.entities.OrderItem;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderItemDomainRequest extends DomainRequest {
    OrderItem orderItem;
}
