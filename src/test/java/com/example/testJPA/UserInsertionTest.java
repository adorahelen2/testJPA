package com.example.testJPA;

import com.example.testJPA.entity.User;
import com.example.testJPA.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserInsertionTest {

    @Autowired
    private UserService userService;

    @Test
    public void insertThousandUsersTest() {
        int numberOfUsers = 100000;

        // Insert 1,000 users
        IntStream.rangeClosed(1, numberOfUsers).forEach(i -> {
            User user = new User();
            user.setUsername("JPA User" + i);
            user.setEmail("JPA" + i + "@easycerti.com");
            user.setPassword("password" + i); // 비밀번호는 테스트 시 간단히 처리
            user.setCreatedAt(LocalDateTime.now());
            userService.saveUser(user);
        });

        // 삽입된 사용자 수 확인
        assertThat(userService.getAllUsers().size()).isGreaterThanOrEqualTo(100000);
    }
}
