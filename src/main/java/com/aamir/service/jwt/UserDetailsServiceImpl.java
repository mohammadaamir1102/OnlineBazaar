package com.aamir.service.jwt;

import com.aamir.entity.User;
import com.aamir.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    final private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findFirstByEmail(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("user not found" + username);

        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(), user.get().getPassword(), new ArrayList<>()
        );
    }
}
