package com.example.testJPA.controller;

import com.example.testJPA.entity.User;
import com.example.testJPA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class MainController {

    @Autowired
    private UserService userService;

    // 사용자 목록 페이지
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    // 새 사용자 추가
    @PostMapping("/add")
    public String addUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // 비밀번호는 해시 처리 필요
        userService.saveUser(user);
        return "redirect:/users";
    }

    // 사용자 삭제
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}