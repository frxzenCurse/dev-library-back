package com.developerLibrary.site.repo;

import com.developerLibrary.site.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepo extends JpaRepository<Content, Long> {
    List<Content> findAllById(Long id);
}
