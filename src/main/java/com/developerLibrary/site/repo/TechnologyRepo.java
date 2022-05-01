package com.developerLibrary.site.repo;

import com.developerLibrary.site.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepo extends JpaRepository<Technology, Long> {
}
