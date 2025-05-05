package com.backend.backend.service;

import java.util.Optional;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
//import com.backend.backend.Validation.DescriptionAlreadyExistsException;
//import com.backend.backend.Validation.TitleAlreadyExistsException;
import com.backend.backend.model.Event;
import com.backend.backend.model.User;
import com.backend.backend.model.Category;
import com.backend.backend.model.CategoryType;
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

    public ResponseEntity<Object> createEvent(Integer userId, Event event) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        // Buscar la categoría basada en el tipo de evento
        CategoryType categoryType;
        try {
            categoryType = CategoryType.valueOf(event.getEventType());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid event type: " + event.getEventType());
        }
        
        // Buscar la categoría correspondiente al tipo de evento
        List<Category> categories = categoryRepository.findAll();
        Category selectedCategory = null;
        
        for (Category category : categories) {
            if (category.getCategoryType() == categoryType) {
                selectedCategory = category;
                break;
            }
        }
        
        if (selectedCategory == null) {
            return ResponseEntity.badRequest().body("No category found for event type: " + event.getEventType());
        }
        
        if (event.getTitle() != null && !eventRepository.findByTitle(event.getTitle()).isEmpty()) {
            return ResponseEntity.badRequest().body("An event with this title already exists");
        }
        
        event.setUser(userOptional.get());
        event.setCategory(selectedCategory);
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
