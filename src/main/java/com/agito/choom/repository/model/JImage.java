package com.agito.choom.repository.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
  @CreationTimestamp
  private Instant createdAt;
}
