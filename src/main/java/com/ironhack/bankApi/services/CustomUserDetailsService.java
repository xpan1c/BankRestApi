package com.ironhack.bankApi.services;

import com.ironhack.bankApi.repositories.UserRepository;
import com.ironhack.bankApi.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!userRepository.findByUsername(username).isPresent()){
            throw new UsernameNotFoundException("User does not exist");
        }
        return new CustomUserDetails(userRepository.findByUsername(username).get());
    }

}
