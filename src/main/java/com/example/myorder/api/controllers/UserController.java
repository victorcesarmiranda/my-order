package com.example.myorder.api.controllers;

import com.example.myorder.RestPath;
import com.example.myorder.api.dtos.CreateUserDto;
import com.example.myorder.api.dtos.UserResponseDto;
import com.example.myorder.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("UserController")
@RequestMapping(RestPath.BASE_PATH + "/users")
@Api(tags = "Usuários")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDto create(
            @ApiParam(value = "Objeto que contém as informações do usuário")
            @RequestBody @Valid CreateUserDto createUserDto) {
        return userService.create(createUserDto);
    }
}
