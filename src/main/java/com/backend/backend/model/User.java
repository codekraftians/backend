package com.backend.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "users")

public class User {
    // Propiedades de la entidad
    // Añadir los arrobas de springboot

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    private int id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must have min 3 and max 20 characters")
    @Pattern(regexp = "[a-zA-Z0-9_.-]+$", message = "Only letters and numbers are allowed")
    private String name;

    @NotBlank(message = "Email address is required")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Must have email address format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    @Pattern(regexp = "^(http|https)://.*$", message = "The image URL must be a valid HTTP or HTTPS URL")
    private String user_image_url;

    // Constructores de la entidad
    public User(int id, String name, String email, String password, String user_image_url) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.user_image_url = user_image_url;
    }

    public User() {

    }

    // Getters y Setters basicos de la entidad

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_image_url() {
        return this.user_image_url;
    }

    public void setUser_image_url(String user_image_url) {
        this.user_image_url = user_image_url;
    }

}
