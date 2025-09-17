package br.edu.insper.musicmeter.user.controller;

import br.edu.insper.musicmeter.user.User;
import br.edu.insper.musicmeter.user.dto.UserSaveDTO;
import br.edu.insper.musicmeter.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void test_getUsers() throws Exception {
        User user = new User(1, "joao", "Joãozinho", new HashSet<>(), new HashSet<>(), null);

        Mockito.when(service.getUsers()).thenReturn(List.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("joao"));
    }

    @Test
    void test_postUser() throws Exception {
        UserSaveDTO dto = new UserSaveDTO("ana", "Aninha", new HashSet<>(), new HashSet<>(), null);
        User user = UserSaveDTO.to(dto);

        Mockito.when(service.saveUser(Mockito.any())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"ana\",\"displayName\":\"Aninha\",\"reviews\":[],\"albums\":[],\"favoriteSong\":null}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ana"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.displayName").value("Aninha"));
    }
}
