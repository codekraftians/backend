package com.backend.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "events")
public class Event {
   

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_seq")
    @SequenceGenerator(name = "events_seq", sequenceName = "events_sequence", allocationSize = 1)
    private int id;


    @NotBlank(message = "Tittle is required")
    @Size(min = 3, max = 50, message = "Tittle must have min 3 and max 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.\\-\\s]+$", message = "Only letters, numbers, spaces, hyphens, underscores, and dots are allowed")// Pendiente de la aprobaci{on del cliente
    private String tittle;


    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 255, message = "Description must have min 3 and max 255 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.\\-\\s]+$", message = "Only letters, numbers, spaces, hyphens, underscores, and dots are allowed")// Pendiente de la aprobacion del cliente
    private String description;

    @NotBlank(message = "Event Date is required")
    @Size(min = 3, max = 20, message = "Event Date must have min 3 and max 20 characters")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Event Date must be in YYYY-MM-DD format")// Pendiente 
    private String event_date;

    @Pattern(regexp = "^(http|https)://.*$", message = "The image URL must be a valid HTTP or HTTPS URL")
    private String events_image_url;

    @NotBlank(message = "Event Time is required")
    @Size(min = 3, max = 10, message = "Event Time must have min 3 and max 10 characters")
    @Pattern(regexp = "^[0-9]{2}:[0-9]{2}$", message = "Event Time must be in HH:MM format")// Pendiente de la ap
    private String event_time;

    @NotBlank(message = "Event Location is required")
    @Size(min = 3, max = 50, message = "Event Location must have min 3 and max 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.\\-\\s]+$", message = "Only letters, numbers, spaces, hyphens, underscores, and dots are allowed")// Pendiente de la aprob
    private String location;

    @NotBlank(message = "Max attendees is required")
    @Size(min = 1, max = 3, message = "Max attendees must have min 1 and max 3 characters")
    @Pattern(regexp = "^[0-9]+$", message = "Max attendees must be a number")// Pendiente de la aprobaci
    private String max_attendees;

    // Relación con la entidadcategory
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

   

    // Constructores de la entidad
    public Event(int id, String tittle, String description, String event_date, String events_image_url, String event_time,
            String location, String max_attendees,Category category, User user) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.event_date = event_date;
        this.events_image_url = events_image_url;
        this.event_time = event_time;
        this.location = location;
        this.max_attendees = max_attendees;
        this.category = category;
        this.user = user;
    }

    public Event() {

    }

    // Getters y Setters basicos de la entidad

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return this.tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getEvent_date() {
        return this.event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvents_image_url() {
        return this.events_image_url;
    }

    public void setEvents_image_url(String events_image_url) {
        this.events_image_url = events_image_url;
    }

    public String getEvent_time() {
        return this.event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMax_attendees() {
        return this.max_attendees;
    }

    public void setMax_attendees(String max_attendees) {
        this.max_attendees = max_attendees;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
