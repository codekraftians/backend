package com.backend.backend.service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import com.backend.backend.exception.CategoryAlreadyExistsException;
import com.backend.backend.model.Category;
import com.backend.backend.repository.CategoryRepository;

@Service

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Object> createCategory(Category category) {

        if(categoryRepository.findByCategory(category.getCategory()) != null){
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
    }

}
