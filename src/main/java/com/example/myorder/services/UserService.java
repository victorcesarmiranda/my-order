package com.example.myorder.services;

import com.example.myorder.api.dtos.CreateUserDto;
import com.example.myorder.api.dtos.UserResponseDto;
import com.example.myorder.api.mappers.UserMapper;
import com.example.myorder.entities.User;
import com.example.myorder.exceptions.AlreadyExistsException;
import com.example.myorder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto create(CreateUserDto createUserDto) {
        validateUserEmail(createUserDto.getEmail());


        User user = userRepository.save(createUser(createUserDto));

        return UserMapper.toResponseDto(user);
    }

    private User createUser(CreateUserDto createUserDto){
        return new User()
                .setName(createUserDto.getName())
                .setEmail(createUserDto.getEmail())
                .setAddress(createUserDto.getAddress())
                .setPassword(createUserDto.getPassword())
                .setPhone(createUserDto.getPhone());
    }

    private void validateUserEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user !=null) {
            throw new AlreadyExistsException("Já existe um usuário cadastrado com esse endereço de email");
        }
    }

}
