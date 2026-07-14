package com.example.tp_integrador.config;

import com.example.tp_integrador.entities.User;
import com.example.tp_integrador.enums.Rol;
import com.example.tp_integrador.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository  userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByEmail("admin@foodstore.com").isEmpty()){

            String password = passwordEncoder.encode("123456");
            User admin = User.builder()
                    .firstName("Super")
                    .lastName("Administrador")
                    .email("admin@foodstore.com")
                    .password(password)
                    .role(Rol.ADMIN)
                    .build();

            userRepository.save(admin);
        }
    }
}
