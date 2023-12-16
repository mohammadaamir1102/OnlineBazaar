package com.aamir.repo;

import com.aamir.entity.User;
import com.aamir.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String username);
    User findByRole(UserRole userRole);
}
