package com.backend.backend.service;

import com.backend.backend.repository.UserRepository;
import com.backend.backend.model.User;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User createUser(User user) {
        // Aquí puedes agregar lógica adicional antes de guardar el usuario, si es
        // necesario
        return userRepository.save(user);
    }

}
