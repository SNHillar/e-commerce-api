package com.example.tp_integrador.services.impl;

import com.example.tp_integrador.dtos.auth.login.LoginRequestDTO;
import com.example.tp_integrador.dtos.auth.login.LoginResponseDto;
import com.example.tp_integrador.dtos.auth.register.RegisterRequestDTO;
import com.example.tp_integrador.dtos.user.UserResponseDto;
import com.example.tp_integrador.entities.User;
import com.example.tp_integrador.enums.Rol;
import com.example.tp_integrador.repositories.UserRepository;
import com.example.tp_integrador.services.AuthService;
import com.example.tp_integrador.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public LoginResponseDto login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.email()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user.getDeleted() != null && user.getDeleted()) {
            throw new IllegalArgumentException("User has been deleted");
        }
        checkPassword(loginRequestDTO, user);
        String accessToken = jwtService.generateToken(user);
        return new LoginResponseDto(accessToken);
    }

    @Override
    public UserResponseDto register(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.findByEmail(registerRequestDTO.email()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use." + registerRequestDTO.email());
        }

        String passwordEncode =  passwordEncoder.encode(registerRequestDTO.password());

        User user = User.builder()
                .firstName(registerRequestDTO.firstName())
                .lastName(registerRequestDTO.lastName())
                .phone(registerRequestDTO.phone())
                .email(registerRequestDTO.email())
                .password(passwordEncode)
                .role(Rol.USER)
                .build();

        userRepository.save(user);
        return UserResponseDto.toDto(user);
    }

    private void checkPassword(LoginRequestDTO loginRequestDTO, User user) {
        if(!passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())){
            throw new IllegalArgumentException("Invalid password.");
        }
    }
}
