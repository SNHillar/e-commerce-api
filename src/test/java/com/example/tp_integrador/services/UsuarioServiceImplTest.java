package com.example.tp_integrador.services;

import com.example.tp_integrador.dtos.usuario.UsuarioCreate;
import com.example.tp_integrador.dtos.usuario.UsuarioDto;
import com.example.tp_integrador.dtos.usuario.UsuarioEdit;
import com.example.tp_integrador.entities.Usuario;
import com.example.tp_integrador.enums.Rol;
import com.example.tp_integrador.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private final UsuarioCreate USER_CREATE = new UsuarioCreate("Jorge", "Secco", "test@test.com", "123456789", "asDweq212", "user");

    private final Usuario USER_PREPARED =  Usuario.builder()
            .id(1L)
            .nombre("Saul")
            .apellido("Hillar")
            .mail("test@test.com")
            .celular("3513525436")
            .password("Testing1234")
            .rol(Rol.ADMIN).build();

    Usuario USER_PREPARED_2 = Usuario.builder()
            .id(2L)
            .nombre("Clara")
            .apellido("Rodriguez")
            .mail("clara@test.com")
            .password("password")
            .rol(Rol.USER)
            .build();

    private final UsuarioEdit USER_EDIT = new UsuarioEdit("Pepe", "Garro", "testing@test.com", "11231241", "password", "user");

    @Test
    void update(){
        // Cuando busquemos en el repositorio el id 1, entonces esperamos que retorne al usuario.
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.ofNullable(USER_PREPARED));
        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenAnswer(invocationOnMock ->  invocationOnMock.getArgument(0));
        UsuarioDto resultDto = usuarioService.update(USER_EDIT, 1L);

        assertEquals(1L, resultDto.id());
        assertEquals("Pepe", resultDto.nombre());
        assertEquals("Garro", resultDto.apellido());
        assertEquals("testing@test.com", resultDto.mail());
        assertEquals("11231241", resultDto.celular());


        Mockito.verify(usuarioRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(Mockito.any(Usuario.class));
    }

    @Test
    void findById(){
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(USER_PREPARED));
        UsuarioDto resultDto = usuarioService.findById(1L);
        assertEquals(1L, resultDto.id());


        Mockito.verify(usuarioRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void findAll(){
        Mockito.when(usuarioRepository.findAll()).thenReturn(Arrays.asList(USER_PREPARED, USER_PREPARED_2));
        List<UsuarioDto> resultDto = usuarioService.findAll();

        assertEquals(2, resultDto.size());
        Mockito.verify(usuarioRepository, Mockito.times(1)).findAll();
    }


    @Test
    void delete(){
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.ofNullable(USER_PREPARED));
        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenAnswer(invocationOnMock ->   invocationOnMock.getArgument(0));

        usuarioService.delete(1L);
        assertTrue(USER_PREPARED.getEliminado());
        Mockito.verify(usuarioRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(Mockito.any(Usuario.class));
    }

    @Test
    void save(){
        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(USER_PREPARED);
        usuarioService.save(USER_CREATE);

        Mockito.verify(usuarioRepository, Mockito.times(1)).save(Mockito.any(Usuario.class));
    }

    @Test
    void findByIdWrong_must_throw_exception(){
        Mockito.when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> usuarioService.findById(99L));
    }
}
