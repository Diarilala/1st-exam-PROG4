package com.agito.choom.endpoint.event.consumer.model;

import com.agito.choom.PojaGenerated;
import com.agito.choom.endpoint.event.model.PojaEvent;

@PojaGenerated
public record TypedEvent(String typeName, PojaEvent payload) {}
