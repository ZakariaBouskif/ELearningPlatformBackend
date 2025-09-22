package com.elearningplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.elearningplatform.entity.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

  
}
