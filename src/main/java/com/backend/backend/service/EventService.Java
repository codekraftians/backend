package com.backend.backend.service;

import java.util.Optional;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.backend.backend.Validation.DescriptionAlreadyExistsException;
import com.backend.backend.Validation.TitleAlreadyExistsException;
import com.backend.backend.model.Event;
import com.backend.backend.model.User;
import com.backend.backend.model.Category;
import com.backend.backend.repository.EventRepository;
import com.backend.backend.repository.UserRepository;
import com.backend.backend.repository.CategoryRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Object> createEvent(Integer userId, Event event, Integer categoryId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (!categoryOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (event.getTitle() != null && eventRepository.findByTitle(event.getTitle()).isPresent()) {
            throw new TitleAlreadyExistsException("[ERROR]: Ya existe un evento con el mismo título en la base de datos");
        }
        
    }

        event.setUser(userOptional.get());
        event.setCategory(categoryOptional.get());
        return new ResponseEntity<>(eventRepository.save(event), HttpStatus.CREATED);
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
    public ResponseEntity<Object> deleteEvent(Integer id) {
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (!eventOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Event event = eventOptional.get();

        eventRepository.deleteById(event.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
