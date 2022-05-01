package com.developerLibrary.site.service;

import com.developerLibrary.site.entity.Category;
import com.developerLibrary.site.entity.Technology;
import com.developerLibrary.site.exceptions.TechNotFoundException;
import com.developerLibrary.site.repo.CategoryRepo;
import com.developerLibrary.site.repo.TechnologyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final TechnologyRepo technologyRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo, TechnologyRepo technologyRepo) {
        this.categoryRepo = categoryRepo;
        this.technologyRepo = technologyRepo;
    }

    public ResponseEntity<List<Category>> getCategories(Long techId) {
        return new ResponseEntity<>(categoryRepo.findAllById(techId), HttpStatus.OK);
    }

    public ResponseEntity<Category> addCategory(Category category, Long techId) {
        Technology technology = technologyRepo.findById(techId)
                .orElseThrow(() -> new TechNotFoundException("Tech with id " + techId + " not found"));
        category.setTechnology(technology);

        return new ResponseEntity<>(categoryRepo.save(category), HttpStatus.CREATED);
    }
}
