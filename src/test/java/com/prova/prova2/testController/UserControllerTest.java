package com.prova.prova2.testController;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prova.prova2.Controller.UserController;
import com.prova.prova2.Model.User;
import com.prova.prova2.Service.UserService;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    private final User user = new User(1L, "usuario", "senha", "ROLE_USER");

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testFindAll() throws Exception {
        List<User> users = List.of(user);
        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk());
        verify(userService).findAll();
    }

    @Test
    void testFindById() throws Exception {
        when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk());
        verify(userService).findById(1L);
    }

    @Test
    void testUpdate() throws Exception {
        User updatedUser = new User(1L, "atualizado", "senhaNova", "ROLE_USER");

        when(userService.update(eq(1L), any(User.class))).thenReturn(updatedUser);

        String jsonBody = """
            {
              "id": 1,
              "username": "atualizado",
              "password": "senhaNova",
              "role": "ROLE_USER"
            }
            """;

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isOk());
        verify(userService).update(eq(1L), any(User.class));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(userService).delete(1L);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk());
        verify(userService).delete(1L);
    }
}
