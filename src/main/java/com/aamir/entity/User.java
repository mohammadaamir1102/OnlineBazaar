package com.aamir.entity;

import com.aamir.enums.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private UserRole role;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] image;
}
