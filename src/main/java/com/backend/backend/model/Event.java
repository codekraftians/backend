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
import jakarta.persistence.Transient;
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


    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must have min 3 and max 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.\\-\\s]+$", message = "Only letters, numbers, spaces, hyphens, underscores, and dots are allowed")
    private String title;


    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 255, message = "Description must have min 3 and max 255 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.\\-\\s]+$", message = "Only letters, numbers, spaces, hyphens, underscores, and dots are allowed")
    private String description;

    @NotBlank(message = "Event Date is required")
    @Size(min = 3, max = 20, message = "Event Date must have min 3 and max 20 characters")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Event Date must be in YYYY-MM-DD format")
    private String eventDate;

    @Pattern(regexp = "^(http|https)://.*$", message = "The image URL must be a valid HTTP or HTTPS URL")
    private String eventsImageUrl;

    @NotBlank(message = "Event Time is required")
    @Size(min = 3, max = 10, message = "Event Time must have min 3 and max 10 characters")
    @Pattern(regexp = "^[0-9]{2}:[0-9]{2}$", message = "Event Time must be in HH:MM format")
    private String eventTime;

    @NotBlank(message = "Event Location is required")
    @Size(min = 3, max = 50, message = "Event Location must have min 3 and max 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.\\-\\s]+$", message = "Only letters, numbers, spaces, hyphens, underscores, and dots are allowed")
    private String location;

    @NotBlank(message = "Max attendees is required")
    @Size(min = 1, max = 3, message = "Max attendees must have min 1 and max 3 characters")
    @Pattern(regexp = "^[0-9]+$", message = "Max attendees must be a number")
    private String maxAttendees;

    // Campo transitorio para recibir el tipo de evento del usuario
    @NotBlank(message = "Event type is required")
    @Pattern(regexp = "^(ONLINE|IN_PERSON)$", message = "Event type must be 'ONLINE' or 'IN_PERSON'")
    @Transient // No se persiste en la base de datos
    private String eventType;

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
    //public Event(int id, String title, String description, String eventDate, String eventsImageUrl, String eventTime,
      //      String location, String maxAttendees, Category category, User user) {
       // this.id = id;
       // this.title = title;
       // this.description = description;
         //this.eventDate = eventDate;
        //this.eventsImageUrl = eventsImageUrl;
        //this.eventTime = eventTime;
        //this.location = location;
        //this.maxAttendees = maxAttendees;
        //this.category = category;
        //this.user = user;
    //}

    public Event() {

    }

    // Getters y Setters basicos de la entidad

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getEventDate() {
        return this.eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventsImageUrl() {
        return this.eventsImageUrl;
    }

    public void setEventsImageUrl(String eventsImageUrl) {
        this.eventsImageUrl = eventsImageUrl;
    }

    public String getEventTime() {
        return this.eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxAttendees() {
        return this.maxAttendees;
    }

    public void setMaxAttendees(String maxAttendees) {
        this.maxAttendees = maxAttendees;
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
    
    public String getEventType() {
       return this.eventType;
   }
    
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
