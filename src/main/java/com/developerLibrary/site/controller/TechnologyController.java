package com.developerLibrary.site.controller;

import com.developerLibrary.site.entity.Technology;
import com.developerLibrary.site.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/technologies")
public class TechnologyController {

    private final TechnologyService service;

    @Autowired
    public TechnologyController(TechnologyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Technology>> getTechnologies() {
        return service.getTechnologies();
    }

    @PostMapping("/add")
    public ResponseEntity<Technology> addTechnologies(@RequestBody Technology technology) {
        return service.addTechnology(technology);
    }
}
