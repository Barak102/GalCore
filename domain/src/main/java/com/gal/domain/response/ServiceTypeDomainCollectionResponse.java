package com.gal.domain.response;

import com.gal.domain.common.base.DomainCollectionResponse;
import com.gal.entities.ServiceType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServiceTypeDomainCollectionResponse extends DomainCollectionResponse {
    List<ServiceType> serviceTypes;
}
