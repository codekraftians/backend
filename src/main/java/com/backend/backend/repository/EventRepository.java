package com.backend.backend.repository;

import com.backend.backend.model.Event;
import com.backend.backend.model.Category;
import com.backend.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

   //segun los requisitos
    List<Event> findByTitle(String title);

    Page<Event> findByCategory(Category category, Pageable pageable);

    Page<Event> findByUser(String name, Pageable pageable);

    Page<Event> findByEventDate(String eventDate, Pageable pageable);

    List<Event> findByUser(User user, Pageable pageable);

    
}
