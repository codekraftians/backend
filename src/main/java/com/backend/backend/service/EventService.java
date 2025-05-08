package com.backend.backend.service;

import java.util.Optional;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.backend.backend.model.Event;
import com.backend.backend.model.User;
import com.backend.backend.repository.EventRepository;
import com.backend.backend.repository.UserRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Event createEvent(Event event, Integer userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return null;
        }

        event.setUser(userOptional.get());

        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    public ResponseEntity<Object> getEventById(Integer id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (!eventOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Event event = eventOptional.get();
        return ResponseEntity.ok(event);
    }

    public ResponseEntity<Object> updateEvent(Integer id, Event updateEvent) {
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (!eventOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Event existingEvent = eventOptional.get();

        existingEvent.setTitle(updateEvent.getTitle());
        existingEvent.setDescription(updateEvent.getDescription());
        eventRepository.save(existingEvent);
        return ResponseEntity.ok(existingEvent);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Object> deleteById(Integer id) {
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (!eventOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Event event = eventOptional.get();

        eventRepository.deleteById(event.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
