package org.tech11.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.tech11.interfaces.ExtendedEmailValidator;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CreateUserDto implements Serializable {

    @ExtendedEmailValidator
    private String email;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    private String otherNames;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotEmpty
    @Size(min = 8)
    private String confirmPassword;

    @NotEmpty
    private LocalDate birthday;

}
