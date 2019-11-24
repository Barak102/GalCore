package com.gal.dto.request.authorization;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDtoRequest {
    String email;
    String password;
}
