package com.gal.dto.response.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TagDtoCollectionResponse {
    List<TagDtoResponse> tags;
}
