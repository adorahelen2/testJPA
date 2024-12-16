package com.example.testJPA.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;    // 사용자 이름
    private String password;    // 암호
    private String email;       // 이메일
    private LocalDateTime createdAt; // 계정 생성 날짜

}