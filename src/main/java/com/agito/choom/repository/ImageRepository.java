package com.agito.choom.repository;

import com.agito.choom.repository.model.JImage;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<JImage, UUID> {
  boolean existsByFilename(String filename);
}
