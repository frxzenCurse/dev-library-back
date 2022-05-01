package com.developerLibrary.site.controller;

import com.developerLibrary.site.entity.Content;
import com.developerLibrary.site.exceptions.InvalidUrlException;
import com.developerLibrary.site.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/content")
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/{catId}")
    public ResponseEntity<List<Content>> getContent(@PathVariable("catId") Long catId) {
        return contentService.getContent(catId);
    }

    @PostMapping("/add")
    public ResponseEntity<Content> addContent(@RequestBody Content content, @RequestParam Long catId) throws IOException, InvalidUrlException {
        return contentService.addContent(content, catId);
    }
}
