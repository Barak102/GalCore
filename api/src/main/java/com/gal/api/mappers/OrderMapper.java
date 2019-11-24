package com.gal.api.mappers;

import com.gal.domain.request.OrderDomainRequest;
import com.gal.domain.response.OrderDomainCollectionResponse;
import com.gal.domain.response.OrderDomainResponse;
import com.gal.dto.request.api.OrderDtoRequest;
import com.gal.dto.response.api.OrderDtoCollectionResponse;
import com.gal.dto.response.api.OrderDtoResponse;
import com.gal.entities.Order;
import com.gal.entities.OrderItem;
import com.gal.entities.ServiceType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(uses = {Order.class , OrderItem.class, ServiceType.class }, componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "order.id", source = "request.id"),
            @Mapping(target = "order.clientId", source = "request.clientId"),
            @Mapping(target = "order.orderGroupId", source = "request.orderGroupId"),
            @Mapping(target = "order.status", source = "request.status"),
            @Mapping(target = "order.dateTime", source = "request.dateTime"),
            @Mapping(target = "order.locationType", source = "request.locationType"),
            @Mapping(target = "order.city", source = "request.city"),
            @Mapping(target = "order.address", source = "request.address")
    })
    OrderDomainRequest DtoToDomain(OrderDtoRequest request);

    @Mappings({
            @Mapping(target = "id", source = "response.order.id"),
            @Mapping(target = "clientId", source = "response.order.clientId"),
            @Mapping(target = "orderGroupId", source = "response.order.orderGroupId"),
            @Mapping(target = "status", source = "response.order.status"),
            @Mapping(target = "dateTime", source = "response.order.dateTime"),
            @Mapping(target = "locationType", source = "response.order.locationType"),
            @Mapping(target = "city", source = "response.order.city"),
            @Mapping(target = "address", source = "response.order.address"),
            @Mapping(target = "orderItems", source = "response.order.orderItems"),
            @Mapping(target = "payments", source = "response.order.payments"),
            @Mapping(target = "tags", source = "response.order.tags")
    })
    OrderDtoResponse DomainToDto(OrderDomainResponse response);

    @Mappings({
            @Mapping(target = "orders", source = "response.orders")
    })
    OrderDtoCollectionResponse DomainCollectionToDtoCollection(OrderDomainCollectionResponse response);
}
