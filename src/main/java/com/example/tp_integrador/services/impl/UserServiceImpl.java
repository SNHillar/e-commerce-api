package com.example.tp_integrador.services.impl;

import com.example.tp_integrador.dtos.user.UserRequestDto;
import com.example.tp_integrador.dtos.user.UserResponseDto;
import com.example.tp_integrador.dtos.user.UserEdit;
import com.example.tp_integrador.entities.User;
import com.example.tp_integrador.repositories.UserRepository;
import com.example.tp_integrador.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = userRequestDto.toEntity();
        userRepository.save(user);
        return UserResponseDto.toDto(user);
    }

    @Override
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return UserResponseDto.toDto(user);
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponseDto::toDto).toList();
    }

    @Override
    public UserResponseDto update(UserEdit userEdit, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        userEdit.applyTo(user);
        userRepository.save(user);
        return UserResponseDto.toDto(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        return UserResponseDto.toDto(user);
    }
}
