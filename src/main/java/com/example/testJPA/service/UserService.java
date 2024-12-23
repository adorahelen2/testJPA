package com.example.testJPA.service;

import com.example.testJPA.entity.User;
import com.example.testJPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Read
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Delete
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
