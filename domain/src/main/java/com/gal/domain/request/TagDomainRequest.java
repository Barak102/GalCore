package com.gal.domain.request;

import com.gal.domain.common.base.DomainRequest;
import com.gal.entities.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDomainRequest extends DomainRequest {
    Tag tag;
}
