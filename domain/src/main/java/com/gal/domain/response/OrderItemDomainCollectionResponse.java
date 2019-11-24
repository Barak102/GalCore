package com.gal.domain.response;

import com.gal.domain.common.base.DomainCollectionResponse;
import com.gal.entities.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderItemDomainCollectionResponse extends DomainCollectionResponse {
    List<OrderItem> orderItems;
}
