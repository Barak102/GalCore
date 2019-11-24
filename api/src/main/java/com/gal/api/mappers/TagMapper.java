package com.gal.api.mappers;

import com.gal.domain.request.TagDomainRequest;
import com.gal.domain.response.TagDomainCollectionResponse;
import com.gal.domain.response.TagDomainResponse;
import com.gal.dto.request.api.TagDtoRequest;
import com.gal.dto.response.api.TagDtoCollectionResponse;
import com.gal.dto.response.api.TagDtoResponse;
import com.gal.entities.Order;
import com.gal.entities.OrderItem;
import com.gal.entities.ServiceType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {Order.class , OrderItem.class, ServiceType.class }, componentModel = "spring")
public interface TagMapper {

    @Mappings({
            @Mapping(target = "tag.id",source = "request.id"),
            //@Mapping(target = "tag.orderId",source = "request.orderId"),
            @Mapping(target = "tag.name",source = "request.name")
    })
    TagDomainRequest DtoToDomain(TagDtoRequest request);

    @Mappings({
            @Mapping(target = "id",source = "response.tag.id"),
            //@Mapping(target = "orderId",source = "response.tag.orderId"),
            @Mapping(target = "name",source = "response.tag.name")
    })
    TagDtoResponse DomainToDto(TagDomainResponse response);

    @Mapping(target = "tags", source="response.tags")
    TagDtoCollectionResponse DomainCollectionToDtoCollection(TagDomainCollectionResponse response);
}
