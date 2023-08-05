package org.tech11.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.tech11.exception.ServiceException;
import org.tech11.model.domain.ApiResponse;
import org.tech11.model.dto.CreateUserDto;
import org.tech11.model.dto.EditPasswordDto;
import org.tech11.model.dto.EditUserDto;
import org.tech11.model.dto.UserDto;
import org.tech11.model.entity.User;
import org.tech11.service.UserService;
import org.tech11.utils.ApiUtils;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users")
@Slf4j
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper = new ModelMapper();

    @GET
    @Path("{id}")
    public ApiResponse<?> getUser(@PathParam("id") String id) {
        log.info("HTTP Request : Get user with id {}", id);

        User user = userService.getUser(id);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        ApiResponse<UserDto> apiResponse= ApiUtils.buildApiResponse(userDto);

        log.info("HTTP Response: Get user with id {}: {}",id, apiResponse);

        return apiResponse;
    }

    @GET
    public ApiResponse<?> getAllUsers() {

        long start = System.currentTimeMillis();
        log.info("HTTP Request : Get all users");

        List<User> users = userService.getAllUsers();

        if (users == null){
            users = new ArrayList<>();
        }

        List<UserDto> userDtos = users
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        ApiResponse<List<UserDto>> apiResponse= ApiUtils.buildApiResponse(userDtos, start);

        log.info("HTTP Response: Get all users:  {}", apiResponse);

        return apiResponse;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResponse<?> createUser(@Valid CreateUserDto createUserDto) {

        log.info("HTTP Request : Create new user with details : {}", createUserDto.toString());

        User user = userService.create(createUserDto);
        if (user == null){
            throw new ServiceException(-1, "sorry, user could not be created ");
        }

        UserDto userDto = modelMapper.map(user, UserDto.class);
        ApiResponse<UserDto> apiResponse= ApiUtils.buildApiResponse(userDto);

        log.info("HTTP Response: Create New User: {}", apiResponse);

        return apiResponse;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResponse<?> modifyUser(@PathParam("id") String id, @Valid EditUserDto editUserDto) {

        log.info("HTTP Request : Update user with id: {}",id);

        User user = userService.updateUser(id,editUserDto);

        UserDto userDto = modelMapper.map(user, UserDto.class);
        ApiResponse<UserDto> apiResponse= ApiUtils.buildApiResponse(userDto);

        log.info("HTTP Response: Update user : {}", apiResponse);

        return apiResponse;
    }



    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResponse<?> deleteUser(@PathParam("id") String id) {

        log.info("HTTP Request : Delete user with id :{}",id);
        long start = System.currentTimeMillis();

        String deleteResponse = userService.deleteUser(id);
        ApiResponse<String> apiResponse= ApiUtils.buildApiResponse(deleteResponse,start);

        log.info("HTTP Response: Delete user :{}", apiResponse);

        return apiResponse;
    }

    @PUT
    @Path("password/reset")
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResponse<UserDto> resetUserPassword(@Valid EditPasswordDto editPasswordDto) {

        log.info("http request: updateUserPassword");

        User user = userService.resetUserPassword(editPasswordDto);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        ApiResponse<UserDto> apiResponse= ApiUtils.buildApiResponse(userDto);

        log.info("http response: updateUserPassword: {}", apiResponse);

        return apiResponse;
    }
}
