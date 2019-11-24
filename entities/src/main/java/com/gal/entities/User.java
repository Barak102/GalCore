package com.gal.entities;

import com.gal.entities.customValidators.EmailValidator;
import com.gal.entities.customValidators.FieldMatch;
import com.gal.entities.customValidators.PasswordValidator;
import com.gal.entities.enums.UserRole;
import com.gal.entities.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
})

public class User {
    int id;
    @NotEmpty()
    @EmailValidator(isRequired = true,message = "Email is not valid")
    String email;
    @Size(min=2,message = "First name must be between 2 to 100 characters")
    String firstName;
    @Size(min=2,message = "Last name must be between 2 to 100 characters")
    String lastName;
    @PasswordValidator(minLength = 6,maxLength = 16, message = "Password is invalid")
    String password;
    String confirmPassword;
    String salt;
    UserRole role;
    UserStatus status;
    @Pattern(regexp = "[+^]?[0-9]{1,3}?[0-9]{7,15}", message = "Phone is not valid")
    String phone;
}
