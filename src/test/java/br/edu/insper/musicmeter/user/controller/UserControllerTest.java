package br.edu.insper.musicmeter.user.controller;


import br.edu.insper.musicmeter.user.User;
import br.edu.insper.musicmeter.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    public UserController controller;

    @Mock
    public UserService service;

    public MockMvc mockMvc;
    
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void test_getAllShouldReturnOneUser() throws Exception {

        User user = new User(
                1,
                "jorge",
                "jorge",
                new HashSet<>(),
                new HashSet<>(),
                null
        );
        Mockito.when(
                service.getUsers()
        ).thenReturn(List.of(user));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/user")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name")
                        .value("jorge"));
    }
}
