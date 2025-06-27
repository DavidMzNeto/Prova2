package com.prova.prova2.testController;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.prova.prova2.Controller.AuthController;
import com.prova.prova2.Dto.AuthRequest;
import com.prova.prova2.Dto.AuthResponse;
import com.prova.prova2.Model.User;
import com.prova.prova2.Repository.UserRepository;
import com.prova.prova2.Service.JwtService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() {
        AuthRequest request = new AuthRequest();
        request.setUsername("usuario");
        request.setPassword("senha");

        when(userRepository.findByUsername("usuario")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("senha")).thenReturn("senhaCodificada");

        ResponseEntity<String> response = authController.register(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Usuário registrado com sucesso!", response.getBody());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUserAlreadyExists() {
        AuthRequest request = new AuthRequest();
        request.setUsername("usuario");

        when(userRepository.findByUsername("usuario"))
            .thenReturn(Optional.of(new User()));

        ResponseEntity<String> response = authController.register(request);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Usuário já existe", response.getBody());
    }

    @Test
    void testLoginSuccess() {
        AuthRequest request = new AuthRequest();
        request.setUsername("usuario");
        request.setPassword("senha");

        User user = new User(1L, "usuario", "senhaCodificada", "ROLE_USER");

        when(userRepository.findByUsername("usuario")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user.getUsername())).thenReturn("token-falso");

        ResponseEntity<AuthResponse> response = authController.login(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("token-falso", response.getBody().getToken());
        verify(authenticationManager).authenticate(any());
    }

    @Test
    void testLoginUserNotFound() {
        AuthRequest request = new AuthRequest();
        request.setUsername("inexistente");

        when(userRepository.findByUsername("inexistente")).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            authController.login(request);
        });
    }
}
