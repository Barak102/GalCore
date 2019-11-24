package com.gal.entities;

import org.hibernate.validator.constraints.NotEmpty;

import com.gal.entities.customValidators.EmailValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredential {
    int id;
    @NotEmpty
    @EmailValidator(isRequired = true,message = "Email is not valid")
    String email;
    String password;
    String salt;
}
