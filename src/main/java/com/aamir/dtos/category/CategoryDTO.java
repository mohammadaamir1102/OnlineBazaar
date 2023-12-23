package com.aamir.dtos.category;

import jakarta.persistence.Lob;
import lombok.Builder;

@Builder
public record CategoryDTO(Long id, String name, @Lob String description) {
}
