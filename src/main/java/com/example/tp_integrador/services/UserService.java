package com.example.tp_integrador.services;

import com.example.tp_integrador.dtos.user.UserRequestDto;
import com.example.tp_integrador.dtos.user.UserResponseDto;
import com.example.tp_integrador.dtos.user.UserEdit;

import java.util.List;


public interface UserService {
    public UserResponseDto save(UserRequestDto userRequestDto);
    public UserResponseDto findById(Long id);
    public List<UserResponseDto> findAll();
    public UserResponseDto update(UserEdit userEdit, Long id);
    public void delete (Long id);
    public UserResponseDto findByEmail(String email);
}
