package com.developerLibrary.site.controller;

import com.developerLibrary.site.entity.Category;
import com.developerLibrary.site.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/{techId}")
    public ResponseEntity<List<Category>> getCategories(@PathVariable("techId") Long techId) {
        return service.getCategories(techId);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category, @RequestParam Long techId) {
        return service.addCategory(category, techId);
    }
}
