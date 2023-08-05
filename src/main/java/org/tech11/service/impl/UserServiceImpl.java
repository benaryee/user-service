package org.tech11.service.impl;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;


import org.tech11.exception.ServiceException;
import org.tech11.model.dto.CreateUserDto;
import org.tech11.model.dto.EditPasswordDto;
import org.tech11.model.dto.EditUserDto;
import org.tech11.model.entity.User;
import org.tech11.repository.UserRepository;
import org.tech11.service.UserService;

import java.util.List;

import static org.tech11.model.enums.ServiceError.*;


@Slf4j
@RequiredArgsConstructor
@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final UserRepository userRepository;


    @Override
    public User getUser(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new ServiceException(404, "no user found with id " + id);
        }

        return user;
    }
    @Override
    public List<User> getAllUsers() {
         return userRepository.findAll().list();
    }

    @Transactional
    @Override
    public User create(CreateUserDto createUserDto) {
        User user = modelMapper.map(createUserDto, User.class);

        if (!createUserDto.getConfirmPassword().equals(createUserDto.getPassword())){
            throw new ServiceException(PASSWORD_MISMATCH);
        }

        if (userRepository.findByUsername(createUserDto.getEmail()).isPresent()){
            throw new ServiceException(USER_ALREADY_EXISTS);
        }

        user.setUsername(createUserDto.getEmail());
        user.setBirthday(createUserDto.getBirthday());
        userRepository.persist(user);
        return user;
    }

    @Transactional
    @Override
    public User updateUser(String id ,EditUserDto editUserDto) {
        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            throw new ServiceException(USER_NOT_FOUND);
        }

        user.setFirstname(editUserDto.getFirstname());
        user.setLastname(editUserDto.getLastname());
        user.setOthernames(editUserDto.getOthernames());

        userRepository.persist(user);

        return user;
    }


    @Override
    @Transactional
    public String deleteUser(String id) {

        User user = getUser(id);
        userRepository.delete(user);

        return "user with id: " + id + " deleted";
    }

    @Override
    public User resetUserPassword(EditPasswordDto editPasswordDto) {
        return null;
    }

}