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

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User createUser(User user) {
        // Aquí puedes agregar lógica adicional antes de guardar el usuario, si es
        // necesario
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        // Verificar si el usuario existe
        return userRepository.findById(id).map(existingUser -> {
            // Actualizar los campos del usuario existente
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setUserImageUrl(user.getUserImageUrl());
            // Guardar los cambios
            return userRepository.save(existingUser);
        }).orElse(null); // Si no existe, devolver null o manejar el error
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}