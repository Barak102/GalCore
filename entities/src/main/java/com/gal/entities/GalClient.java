package com.gal.entities;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.gal.entities.customValidators.EmailValidator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GalClient {
    int id;
    @Size(min=2,message = "First name must be between 2 to 100 characters")
    String firstName;
    @Size(min=2,message = "Last name must be between 2 to 100 characters")
    String lastName;
    @Size(min=2,message = "City must be between 2 to 100 characters")
    String city;
    @Size(min=2,message = "Address must be between 2 to 100 characters")
    String address;
    @EmailValidator(message = "Email is not valid",isRequired = true)
    String email;
    @Pattern(regexp = "[+^]?[0-9]{1,3}?[0-9]{7,15}", message = "Phone is not valid")
    String phone;
    List<Order> orders;

}
