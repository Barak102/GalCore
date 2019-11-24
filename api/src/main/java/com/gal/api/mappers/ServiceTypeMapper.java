package com.gal.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.gal.domain.request.ServiceTypeDomainRequest;
import com.gal.domain.response.ServiceTypeDomainCollectionResponse;
import com.gal.domain.response.ServiceTypeDomainResponse;
import com.gal.dto.request.api.ServiceTypeDtoRequest;
import com.gal.dto.response.api.ServiceTypeDtoCollectionResponse;
import com.gal.dto.response.api.ServiceTypeDtoResponse;
import com.gal.entities.Order;
import com.gal.entities.ServiceType;

@Mapper(uses = {Order.class , ServiceType.class }, componentModel = "spring")
public interface ServiceTypeMapper {

    @Mappings({
            @Mapping(target = "serviceType.id",source = "request.id"),
            @Mapping(target = "serviceType.name",source = "request.name"),
            @Mapping(target = "serviceType.description",source = "request.description"),
            @Mapping(target = "serviceType.price", source = "request.price")
    })
    ServiceTypeDomainRequest DtoToDomain(ServiceTypeDtoRequest request);

    @Mappings({
            @Mapping(target = "id",source = "response.serviceType.id"),
            @Mapping(target = "name",source = "response.serviceType.name"),
            @Mapping(target = "description",source = "response.serviceType.description"),
            @Mapping(target = "price",source = "response.serviceType.price")
    })
    ServiceTypeDtoResponse DomainToDto(ServiceTypeDomainResponse response);

    @Mapping(target = "serviceTypes", source="response.serviceTypes")
    ServiceTypeDtoCollectionResponse DomainCollectionToDtoCollection(ServiceTypeDomainCollectionResponse response);
}
