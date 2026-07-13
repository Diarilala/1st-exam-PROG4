package com.agito.choom.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Instant createdAt;
}
