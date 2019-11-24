package com.gal.api.mappers;

import com.gal.dto.response.api.ClientDtoCollectionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gal.domain.request.ClientDomainRequest;
import com.gal.domain.response.ClientDomainCollectionResponse;
import com.gal.domain.response.ClientDomainResponse;
import com.gal.dto.request.api.ClientDtoRequest;
import com.gal.dto.response.api.ClientDtoResponse;
import com.gal.entities.GalClient;
import org.mapstruct.Mappings;

@Mapper(uses = GalClient.class,componentModel = "spring")
public interface ClientMapper {

    @Mappings({
            @Mapping(target = "client.id",source = "request.id"),
            @Mapping(target = "client.firstName",source = "request.firstName"),
            @Mapping(target = "client.lastName",source = "request.lastName"),
            @Mapping(target = "client.city", source = "request.city"),
            @Mapping(target = "client.address", source = "request.address"),
            @Mapping(target = "client.email", source = "request.email"),
            @Mapping(target = "client.phone", source = "request.phone")
    })
    ClientDomainRequest DtoToDomain(ClientDtoRequest request);

    @Mappings({
            @Mapping(target = "id",source = "response.client.id"),
            @Mapping(target = "firstName",source = "response.client.firstName"),
            @Mapping(target = "lastName",source = "response.client.lastName"),
            @Mapping(target = "city",source = "response.client.city"),
            @Mapping(target = "address",source = "response.client.address"),
            @Mapping(target = "email",source = "response.client.email"),
            @Mapping(target = "phone",source = "response.client.phone"),
            @Mapping(target = "orders",source = "response.client.orders")
    })
    ClientDtoResponse DomainToDto(ClientDomainResponse response);

    @Mapping(target = "clients", source="response.clients")
    ClientDtoCollectionResponse DomainCollectionToDtoCollection(ClientDomainCollectionResponse response);
}
