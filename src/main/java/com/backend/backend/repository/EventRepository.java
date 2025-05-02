package com.backend.backend.repository;

import com.backend.backend.model.Event;
import com.backend.backend.model.Category;
import com.backend.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

   //segun los requisitos
    Page<Event> findByTittle(String tittle);//mostrar xtitulo

    Page<Event> findByCategory(Category category);//mostrar xcategoria

    Page<Event> findByUser(String name);//mostrar xusuario

    Page<Event> findByEvent_date(String date);//mostrar xfecha

    List<Event> findByUser(User user);
}
