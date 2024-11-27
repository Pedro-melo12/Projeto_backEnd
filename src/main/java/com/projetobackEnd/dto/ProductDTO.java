package com.projetobackEnd.dto;

import java.util.UUID;

public record ProductDTO(UUID id, String name, String description, int price) {
}
