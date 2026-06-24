package com.example.tp_integrador.dtos.auth.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(

        @NotBlank(message = "El nombre no puede estar vacio.")
        String firstName,
        @NotBlank(message = "El apellido no puede estar vacio.")
        String lastName,
        @Size(min = 10, max = 10, message = "El numero debe contener 10 digitos.")
        @Pattern(regexp = "\\d+", message = "Formato de celular invalido.")
        String phoneNumber,

        @NotBlank(message = "El email no puede estar vacio.")
        @Email(message = "Formato de email invalido.")
        String email,

        @NotBlank
        @Size(min = 6, message = "La clave debe contener al menos 6 digitos.")
        String password
) {
}
