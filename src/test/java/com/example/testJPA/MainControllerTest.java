package com.example.testJPA;

import com.example.testJPA.controller.MainController;
import com.example.testJPA.entity.User;
import com.example.testJPA.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MainControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private MainController mainController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void testCreateUserConcurrent() throws Exception {
        // Given
        User user = new User();
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");

        User savedUser = new User();
        savedUser.setId(1);
        savedUser.setName("John Doe");
        savedUser.setEmail("johndoe@example.com");

        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        // Executor service to simulate concurrent requests
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    try {
                        mockMvc.perform(post("/users")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(user)))
                                .andExpect(status().isOk());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        // Shutdown executor service
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(100);
        }

        // Verify that saveUser was called exactly 10,000 times
        verify(userService, times(10000)).saveUser(Mockito.any(User.class));
    }

}
