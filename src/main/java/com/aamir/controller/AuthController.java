package com.aamir.controller;

import com.aamir.dto.AuthenticationDTO;
import com.aamir.dto.SignupDTO;
import com.aamir.dto.UserDTO;
import com.aamir.entity.User;
import com.aamir.repo.UserRepository;
import com.aamir.service.auth.AuthService;
import com.aamir.utils.JWTUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtil jwtUtil;
    final public static String TOKEN_PREFIX = "Bearer ";
    final public static String HEADER_STRING = "Authorization";
    @Autowired
    private AuthService authService;


    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationDTO authDTO,
                                          HttpServletResponse response) throws IOException, JSONException {

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken
                            (authDTO.getUsername(), authDTO.getPassword()));

        } catch (Exception e) {
            throw new BadCredentialsException("Incorrect Username & Password.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authDTO.getUsername());
        Optional<User> userOptional = userRepository.findFirstByEmail(userDetails.getUsername());
        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        if (userOptional.isPresent()) {
            response.getWriter()
                    .write(new JSONObject()
                            .put("userid", userOptional.get().getId())
                            .put("role", userOptional.get().getRole())
                            .toString()
                    );
        }
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, "
                + "X-Requested-With, Content-Type, Accept, X-Custom-header");
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);

    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO) {
        if (authService.hasUserWithEmail(signupDTO.email())) {
            return new ResponseEntity<>("User Already Exist.", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDTO user = authService.createUser(signupDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
