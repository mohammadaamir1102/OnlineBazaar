package com.aamir.dtos.auth;

import com.aamir.enums.UserRole;
import lombok.Builder;

@Builder
public record UserDTO(Long id, String email, String name, UserRole userRole) {
}
