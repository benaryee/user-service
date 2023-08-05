package org.tech11.service;

import org.tech11.model.dto.CreateUserDto;
import org.tech11.model.dto.EditPasswordDto;
import org.tech11.model.dto.EditUserDto;
import org.tech11.model.entity.User;

import java.util.List;

public interface UserService {

    User getUser(String id);
    
    List<User> getAllUsers();

    User create(CreateUserDto createUser);

    User updateUser(String id,EditUserDto editUserDto);

    String deleteUser(String id);

    User resetUserPassword(EditPasswordDto editPasswordDto);
}
