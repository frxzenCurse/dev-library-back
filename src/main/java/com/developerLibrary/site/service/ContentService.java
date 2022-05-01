package com.developerLibrary.site.service;

import com.developerLibrary.site.entity.Category;
import com.developerLibrary.site.entity.Content;
import com.developerLibrary.site.exceptions.ContentNotFoundException;
import com.developerLibrary.site.exceptions.InvalidUrlException;
import com.developerLibrary.site.repo.CategoryRepo;
import com.developerLibrary.site.repo.ContentRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContentService {

    private final CategoryRepo categoryRepo;
    private final ContentRepo contentRepo;

    @Autowired
    public ContentService(CategoryRepo categoryRepo, ContentRepo contentRepo) {
        this.categoryRepo = categoryRepo;
        this.contentRepo = contentRepo;
    }

    public ResponseEntity<List<Content>> getContent(Long catId) {
        Category category = categoryRepo.findById(catId)
                .orElseThrow(() -> new ContentNotFoundException("Category with id " + catId + " is not found"));

        return new ResponseEntity<>(category.getContents(), HttpStatus.OK);
    }

    public ResponseEntity<Content> addContent(Content content, Long catId) throws InvalidUrlException, IOException {
        String URL_REGEXP = "(https|http|ftp):\\/\\/.+\\.\\w+";
        Pattern pattern = Pattern.compile(URL_REGEXP, Pattern.CASE_INSENSITIVE);
        Matcher url = pattern.matcher(content.getUrl());

        if (!url.find()) {
            throw new InvalidUrlException("Некорректный url адрес");
        }

        Document doc = Jsoup.connect(content.getUrl()).get();
        Element title = doc.select("meta[name$=title],meta[property$=title]").first();
        Element image = doc.select("meta[name$=image],meta[property$=image]").first();
        StringBuilder imagePath = new StringBuilder(getMeta(image));

        if (!pattern.matcher(imagePath.toString()).find()) {
            imagePath.insert(0, url.group());
        }

        content.setTitle(getMeta(title));
        content.setImage(imagePath.toString());

        Category category = categoryRepo.findById(catId)
                .orElseThrow(() -> new ContentNotFoundException("Category with id " + catId + " is not found"));
        content.setCategory(category);

        return new ResponseEntity<>(contentRepo.save(content), HttpStatus.CREATED);
    }

    private String getMeta(Element element) {
        return element == null ? "" : element.attr("content");
    }
}
