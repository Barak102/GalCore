package com.gal.dto.response.authorization;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDtoCollectionResponse {
    List<UserDtoResponse> users;
}
