package com.agito.choom.mapper;

import com.agito.choom.model.Image;
import com.agito.choom.repository.model.JImage;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ImageMapper {
  public Image toModel(JImage entity) {
    return Image.builder()
        .id(entity.getId())
        .filename(entity.getFilename())
        .email(entity.getEmail())
        .createdAt(entity.getCreatedAt())
        .build();
  }

  public List<Image> toModel(List<JImage> entities) {
    return entities.stream().map(this::toModel).toList();
  }

  public JImage toEntity(Image model) {
    return JImage.builder()
        .id(model.id())
        .filename(model.filename())
        .email(model.email())
        .createdAt(model.createdAt())
        .build();
  }

  public List<JImage> toEntity(List<Image> models) {
    return models.stream().map(this::toEntity).toList();
  }
}
