package com.gal.api.mappers;

import com.gal.domain.request.OrderItemDomainRequest;
import com.gal.domain.response.OrderItemDomainCollectionResponse;
import com.gal.domain.response.OrderItemDomainResponse;
import com.gal.dto.request.api.OrderItemDtoRequest;
import com.gal.dto.response.api.OrderItemDtoCollectionResponse;
import com.gal.dto.response.api.OrderItemDtoResponse;
import com.gal.entities.GalClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = GalClient.class,componentModel = "spring")
public interface OrderItemMapper {

    @Mappings({
         @Mapping(target = "orderItem.id",source = "request.id"),
         @Mapping(target = "orderItem.orderId",source = "request.orderId"),
         @Mapping(target = "orderItem.serviceType.id",source = "request.serviceTypeId"),
         @Mapping(target = "orderItem.price", source = "request.price"),
         @Mapping(target = "orderItem.description", source = "request.description")
    })
    OrderItemDomainRequest DtoToDomain(OrderItemDtoRequest request);

    @Mappings({
            @Mapping(target = "id",source = "response.orderItem.id"),
            @Mapping(target = "orderId",source = "response.orderItem.orderId"),
            @Mapping(target = "serviceType.id",source = "response.orderItem.serviceType.id"),
            @Mapping(target = "serviceType.name",source = "response.orderItem.serviceType.name"),
            @Mapping(target = "serviceType.description",source = "response.orderItem.serviceType.description"),
            @Mapping(target = "price", source = "response.orderItem.price"),
            @Mapping(target = "description", source = "response.orderItem.description")
    })
    OrderItemDtoResponse DomainToDto(OrderItemDomainResponse response);

    @Mapping(target = "orderItems", source="response.orderItems")
    OrderItemDtoCollectionResponse DomainCollectionToDtoCollection(OrderItemDomainCollectionResponse response);
}
