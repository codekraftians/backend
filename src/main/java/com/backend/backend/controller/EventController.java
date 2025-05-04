package com.backend.backend.controller;
import org.springframework.web.bind.annotation.RestController;
import com.backend.backend.model.Event;
import com.backend.backend.service.EventService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;

    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Object> createEvent(@PathVariable Integer userId, @Valid @RequestBody Event event) {
        Integer categoryId = event.getCategory().getId();
        return eventService.createEvent(userId, event, categoryId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEventById(@PathVariable Integer id) {
        return this.eventService.getEventById(id);
    }

    @GetMapping
    public List<Event> getAllEvent() {
        return eventService.getAllEvents();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateEvent(@PathVariable Integer id, @RequestBody Event updatedEvent) {
        return this.eventService.updateEvent(id, updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        return this.eventService.deleteById(id);
    }
}
