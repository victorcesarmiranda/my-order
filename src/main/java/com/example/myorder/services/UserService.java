package com.example.myorder.services;

import com.example.myorder.api.dtos.CreateUserDto;
import com.example.myorder.api.dtos.UserResponseDto;
import com.example.myorder.api.mappers.UserMapper;
import com.example.myorder.entities.User;
import com.example.myorder.exceptions.AlreadyExistsException;
import com.example.myorder.exceptions.NotFondException;
import com.example.myorder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public UserResponseDto getById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new NotFondException("Não existe usuário com o id: " + id);
        }
        return UserMapper.toResponseDto(optionalUser.get());
    }

    public List<UserResponseDto> listAll() {
       List<User> users = userRepository.findAll();
//        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
//
//        for (User user : users) {
//            UserResponseDto userResponseDto = new UserResponseDto();
//            userResponseDto.setName(user.getName())
//                    .setAddress(user.getAddress())
//                    .setPhone(user.getPhone())
//                    .setEmail(user.getEmail())
//                    .setId(user.getId());
//
//            userResponseDtoList.add(userResponseDto);
//
//        }
//        return userResponseDtoList;
        return users.stream().map(UserMapper::toResponseDto).collect(Collectors.toList());
    }

    private void validateUserEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user !=null) {
            throw new AlreadyExistsException("Já existe um usuário cadastrado com esse endereço de email");
        }
    }
}
