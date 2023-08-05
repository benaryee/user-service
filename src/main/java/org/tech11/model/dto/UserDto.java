package org.tech11.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


@Data
public class UserDto implements Serializable {

    private String id;
    private String username;
    private String email;
    private String firstname;
    private LocalDate birthday;
    private String lastname;
    private String othernames;
    private String created;
    private String updated;
}
