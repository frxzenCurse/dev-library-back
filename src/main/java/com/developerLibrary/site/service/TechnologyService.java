package com.developerLibrary.site.service;

import com.developerLibrary.site.entity.Technology;
import com.developerLibrary.site.repo.TechnologyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService {

    private final TechnologyRepo repository;

    @Autowired
    public TechnologyService(TechnologyRepo repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<Technology>> getTechnologies() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Technology> addTechnology(Technology technology) {
        return new ResponseEntity<>(repository.save(technology), HttpStatus.CREATED);
    }
}
