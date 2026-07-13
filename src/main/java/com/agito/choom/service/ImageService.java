package com.agito.choom.service;

import com.agito.choom.endpoint.event.EventProducer;
import com.agito.choom.endpoint.event.model.ImageUploaded;
import com.agito.choom.file.bucket.BucketComponent;
import com.agito.choom.mapper.ImageMapper;
import com.agito.choom.model.Image;
import com.agito.choom.repository.ImageRepository;
import com.agito.choom.repository.model.JImage;
import jakarta.ws.rs.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageService {
    private final ImageRepository repository;
    private final ImageMapper mapper;
    private final EventProducer<ImageUploaded> eventProducer;
    private final BucketComponent bucketComponent;

    public Image upload(String email, MultipartFile file) {
        String contentType = file.getContentType();
        if(contentType == null || (!contentType.equals("image/jpeg") && !contentType.equals("image/png"))) {
            throw new BadRequestException("Invalid content type");
        }

        File tempFile = convertToFile(file);

        String bucketKey = "images/" + UUID.randomUUID() + "/" + file.getOriginalFilename();
        bucketComponent.upload(tempFile, bucketKey);

        String imageUrl = bucketComponent
                .presign(bucketKey, Duration.ofHours(24))
                .toString();

        JImage entity = JImage.builder()
                .id(UUID.randomUUID())
                .filename(imageUrl)
                .email(email)
                .build();
        Image saved = mapper.toModel(repository.save(entity));

        eventProducer.accept(List.of(new ImageUploaded(saved)));

        tempFile.delete();

        return saved;
    }

    @SneakyThrows
    public File convertToFile(MultipartFile multipartFile) {
        File tempFile = File.createTempFile("upload-", "-" + multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);
        return tempFile;
    }
}
