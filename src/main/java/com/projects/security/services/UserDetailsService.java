package com.projects.security.services;


import com.projects.security.entity.User;
import com.projects.security.repositories.UserRepository;
import com.projects.security.security.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
// todo - import
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        // todo - import
        return new com.projects.security.security.UserDetails(user.get());
    }
}
