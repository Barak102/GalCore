package com.gal.dto.request.authorization;

import com.gal.entities.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoRequest {
    int id;
    String email;
    String firstName;
    String lastName;
    String password;
    String confirmPassword;
    UserRole role;
    String phone;
}
