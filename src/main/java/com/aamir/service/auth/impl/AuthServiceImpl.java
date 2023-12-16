package com.aamir.service.auth.impl;

import com.aamir.dto.SignupDTO;
import com.aamir.dto.UserDTO;
import com.aamir.entity.User;
import com.aamir.enums.UserRole;
import com.aamir.mapper.UserMapper;
import com.aamir.repo.UserRepository;
import com.aamir.service.auth.AuthService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    final private UserRepository userRepository;
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO createUser(SignupDTO signupDTO) {
        User user = User.builder()
                .email(signupDTO.email())
                .name(signupDTO.name())
                .password(bCryptPasswordEncoder.encode(signupDTO.password()))
                .role(UserRole.ADMIN)
                .build();

        return UserMapper.userEntityToUserDTO(userRepository.save(user));
    }

    @Override
    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct // creating admin data internally
    public void createAdminAccount() {
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if (null == adminAccount){
            User user = new User();
            user.setEmail("aamir@gmail.com");
            user.setName("Aamir Khan");
            user.setRole(UserRole.ADMIN);
            user.setPassword(bCryptPasswordEncoder.encode("aamir"));
            userRepository.save(user);
        }
    }
}
