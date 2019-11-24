package com.gal.domain.response;

import com.gal.domain.common.base.DomainCollectionResponse;
import com.gal.entities.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDomainCollectionResponse extends DomainCollectionResponse {
    List<Order> orders;
}
