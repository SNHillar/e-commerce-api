package com.example.tp_integrador.controllers;

import com.example.tp_integrador.dtos.auth.login.LoginRequestDTO;
import com.example.tp_integrador.dtos.auth.register.RegisterRequestDTO;
import com.example.tp_integrador.dtos.user.UserResponseDto;
import com.example.tp_integrador.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity <UserResponseDto> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        UserResponseDto userResponseDto = authService.register(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        UserResponseDto userResponseDto = authService.login(loginRequestDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponseDto);
    }
}
