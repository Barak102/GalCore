package com.gal.domain.response;

import com.gal.domain.common.base.DomainResponse;
import com.gal.entities.ServiceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceTypeDomainResponse extends DomainResponse {
    ServiceType serviceType;
}
