package com.aamir.service.auth;

import com.aamir.dto.SignupDTO;
import com.aamir.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);

    Boolean hasUserWithEmail(String email);

}
