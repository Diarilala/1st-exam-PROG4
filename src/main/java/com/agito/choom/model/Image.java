package com.agito.choom.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Image(UUID id, String filename, String email) {
}
