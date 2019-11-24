package com.gal.api.mappers;

import com.gal.domain.request.PaymentDomainRequest;
import com.gal.domain.response.PaymentDomainCollectionResponse;
import com.gal.domain.response.PaymentDomainResponse;
import com.gal.dto.request.api.PaymentDtoRequest;
import com.gal.dto.response.api.PaymentDtoCollectionResponse;
import com.gal.dto.response.api.PaymentDtoResponse;
import com.gal.entities.Order;
import com.gal.entities.OrderItem;
import com.gal.entities.ServiceType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {Order.class , OrderItem.class, ServiceType.class }, componentModel = "spring")
public interface PaymentMapper {

    @Mappings({
            @Mapping(target = "payment.id", source = "request.id"),
            @Mapping(target = "payment.orderId", source = "request.orderId"),
            @Mapping(target = "payment.amount", source = "request.amount"),
            @Mapping(target = "payment.paymentMethod", source = "request.paymentMethod")
    })
    PaymentDomainRequest DtoToDomain(PaymentDtoRequest request);

    @Mappings({
            @Mapping(target = "id", source = "response.payment.id"),
            @Mapping(target = "orderId", source = "response.payment.orderId"),
            @Mapping(target = "amount", source = "response.payment.amount"),
            @Mapping(target = "paymentMethod", source = "response.payment.paymentMethod")
    })
    PaymentDtoResponse DomainToDto(PaymentDomainResponse response);

    @Mappings({
            @Mapping(target = "payments", source = "response.payments")
    })
    PaymentDtoCollectionResponse DomainCollectionToDtoCollection(PaymentDomainCollectionResponse response);
}
