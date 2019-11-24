package com.gal.authorization.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.gal.domain.request.UserDomainRequest;
import com.gal.domain.response.UserDomainCollectionResponse;
import com.gal.domain.response.UserDomainResponse;
import com.gal.dto.request.authorization.UserDtoRequest;
import com.gal.dto.response.authorization.UserDtoCollectionResponse;
import com.gal.dto.response.authorization.UserDtoResponse;
import com.gal.entities.User;

@Mapper(uses = User.class, componentModel = "spring")
public interface UserMapper {

    @Mappings({
        @Mapping(target = "user.id", source = "request.id"),
        @Mapping(target = "user.email", source = "request.email"),
        @Mapping(target = "user.firstName", source = "request.firstName"),
        @Mapping(target = "user.lastName", source = "request.lastName"),
        @Mapping(target = "user.password", source = "request.password"),
        @Mapping(target = "user.confirmPassword", source = "request.confirmPassword"),
        @Mapping(target = "user.role", source = "request.role"),
        @Mapping(target = "user.phone", source = "request.phone")
    })
    UserDomainRequest DtoToDomain(UserDtoRequest request);

    @Mappings({
        @Mapping(target = "id", source = "response.user.id"),
        @Mapping(target = "email", source = "response.user.email"),
        @Mapping(target = "firstName", source = "response.user.firstName"),
        @Mapping(target = "lastName", source = "response.user.lastName"),
        @Mapping(target = "status",source = "response.user.status"),
        @Mapping(target = "role", source = "response.user.role"),
        @Mapping(target = "phone", source = "response.user.phone")
    })
    UserDtoResponse DomainToDto(UserDomainResponse response);

    @Mapping(target = "users", source = "response.users")
    UserDtoCollectionResponse DomainCollectionToDtoCollection(UserDomainCollectionResponse response);
}
