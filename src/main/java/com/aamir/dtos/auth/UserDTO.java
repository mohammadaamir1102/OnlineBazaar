package com.aamir.dto;

import com.aamir.enums.UserRole;
import lombok.Builder;

@Builder
public record UserDTO(Long id, String email, String name, UserRole userRole) {
}
