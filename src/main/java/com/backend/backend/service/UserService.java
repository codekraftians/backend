package com.backend.backend.service;

import com.backend.backend.repository.UserRepository;

import org.springframework.stereotype.Service;

import com.backend.backend.model.User;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmailAndPassword(String email, String password) {

        User userEmail = userRepository.findByEmail(email);
        if (userEmail == null) {
            return null;
        } else if (userEmail.getPassword().equals(password)) {
            return userEmail;
        } else {
            return null;
        }
    }

    public User createUser(User user) {

        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {

        return userRepository.findById(id).map(existingUser -> {

            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setUserImageUrl(user.getUserImageUrl());

            return userRepository.save(existingUser);
        }).orElse(null);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}