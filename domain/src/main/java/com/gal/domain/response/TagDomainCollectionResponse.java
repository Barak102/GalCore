package com.gal.domain.response;

import com.gal.domain.common.base.DomainCollectionResponse;
import com.gal.entities.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TagDomainCollectionResponse extends DomainCollectionResponse {
    List<Tag> tags;
}
