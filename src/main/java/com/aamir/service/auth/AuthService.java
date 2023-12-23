package com.aamir.service.auth;

import com.aamir.dtos.auth.SignupDTO;
import com.aamir.dtos.auth.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);

    Boolean hasUserWithEmail(String email);

}
