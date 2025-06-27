package com.prova.prova2.testService;

import org.junit.jupiter.api.*;

import com.prova.prova2.Model.User;
import com.prova.prova2.Service.JwtService;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setup() {
        jwtService = new JwtService();
    }

    @Test
    void testGenerateAndExtractToken() {
        User user = new User(1L, "usuario", "senha", "ROLE_USER");

        String token = jwtService.generateToken(user.getUsername());
        assertNotNull(token);

        String username = jwtService.extractUsername(token);
        assertEquals("usuario", username);
    }
}