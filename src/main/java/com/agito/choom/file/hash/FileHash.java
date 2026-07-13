package com.agito.choom.file.hash;

import com.agito.choom.PojaGenerated;

@PojaGenerated
public record FileHash(FileHashAlgorithm algorithm, String value) {}
