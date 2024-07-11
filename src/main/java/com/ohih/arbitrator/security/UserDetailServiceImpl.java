package com.ohih.arbitrator.security;

import com.ohih.arbitrator.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetailsImpl userDetails = userService.getUserByEmail(email);
        if (userDetails == null) {
            throw new UsernameNotFoundException(email);
        }
        return org.springframework.security.core.userdetails.User.withUsername(userDetails.getEmail())
                .password(userDetails.getPassword())
                .authorities(userDetails.getAuthorities())
                .build();
    }
}
