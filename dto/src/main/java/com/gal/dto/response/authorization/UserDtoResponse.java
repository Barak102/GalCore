package com.gal.dto.response.authorization;

import com.gal.entities.enums.UserRole;
import com.gal.entities.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoResponse {
    int id;
    String email;
    String firstName;
    String lastName;
    UserRole role;
    UserStatus status;
    String phone;
}
