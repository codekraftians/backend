package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.backend.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  
    User findByEmail(String email); // Método para encontrar un usuario por su correo electrónico

}
