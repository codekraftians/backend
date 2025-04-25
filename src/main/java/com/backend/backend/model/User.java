package com.backend.backend.model;

public class User {
    // Propiedades de la entidad
    private int id;
    private String name;
    private String email;
    private String password;
    private String user_image_url;

    // Constructor de la entidad
    public User(int id, String name, String email, String password, String user_image_url) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.user_image_url = user_image_url;
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
