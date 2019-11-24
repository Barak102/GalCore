package com.gal.domain.response;

import com.gal.domain.common.base.DomainResponse;
import com.gal.entities.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDomainResponse extends DomainResponse {
    Tag tag;
}
