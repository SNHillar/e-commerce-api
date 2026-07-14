package com.example.tp_integrador.services;


import com.example.tp_integrador.dtos.auth.login.LoginRequestDTO;
import com.example.tp_integrador.dtos.auth.login.LoginResponseDto;
import com.example.tp_integrador.dtos.auth.register.RegisterRequestDTO;
import com.example.tp_integrador.dtos.user.UserResponseDto;

public interface AuthService {
    public LoginResponseDto login(LoginRequestDTO loginRequestDTO);
    public UserResponseDto register(RegisterRequestDTO registerRequestDTO);
}
