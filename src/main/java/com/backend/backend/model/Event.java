package com.backend.backend.model;

import jakarta.persistence.Column;

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
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_seq")
    @SequenceGenerator(name = "events_seq", sequenceName = "events_sequence", allocationSize = 1)
    private int id;

    @Column
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must have min 3 and max 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.\\-\\s]+$", message = "Only letters, numbers, spaces, hyphens, underscores, and dots are allowed")
    private String title;

    @Column
    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 255, message = "Description must have min 3 and max 255 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.\\-\\s]+$", message = "Only letters, numbers, spaces, hyphens, underscores, and dots are allowed")
    private String description;

    @Column
    @NotBlank(message = "Event Date is required")
    @Size(min = 3, max = 20, message = "Event Date must have min 3 and max 20 characters")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Event Date must be in YYYY-MM-DD format")
    private String eventDate;

    @Column
    @Pattern(regexp = "^(http|https)://.*$", message = "The image URL must be a valid HTTP or HTTPS URL")
    private String eventsImageUrl;

    @Column
    @NotBlank(message = "Event Time is required")
    @Size(min = 3, max = 10, message = "Event Time must have min 3 and max 10 characters")
    @Pattern(regexp = "^[0-9]{2}:[0-9]{2}$", message = "Event Time must be in HH:MM format")
    private String eventTime;

    @Column
    @NotBlank(message = "Event Location is required")
    @Size(min = 3, max = 50, message = "Event Location must have min 3 and max 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.\\-\\s]+$", message = "Only letters, numbers, spaces, hyphens, underscores, and dots are allowed")
    private String location;

    @Column
    @NotBlank(message = "Max attendees is required")
    @Size(min = 1, max = 3, message = "Max attendees must have min 1 and max 3 characters")
    @Pattern(regexp = "^[0-9]+$", message = "Max attendees must be a number")
    private String maxAttendees;

    @Column(nullable = false)
    @NotNull(message = "Event type is required")
    @Enumerated(EnumType.STRING)
    private CategoryType eventType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Event() {

    }

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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CategoryType getEventType() {
        return this.eventType;
    }

    public void setEventType(CategoryType eventType) {
        this.eventType = eventType;
    }
}
