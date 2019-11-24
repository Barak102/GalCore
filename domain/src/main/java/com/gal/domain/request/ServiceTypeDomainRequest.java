package com.gal.domain.request;

import com.gal.domain.common.base.DomainRequest;
import com.gal.entities.ServiceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceTypeDomainRequest extends DomainRequest {
    ServiceType serviceType;
}
