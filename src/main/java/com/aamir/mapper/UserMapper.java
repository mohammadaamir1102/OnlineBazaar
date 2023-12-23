package com.aamir.mapper;

import com.aamir.dtos.auth.UserDTO;
import com.aamir.entity.User;

public class UserMapper {

    public static UserDTO userEntityToUserDTO(User user) {
        if (null == user) {
            return null;
        }
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .userRole(user.getRole())
                .build();
    }
}
