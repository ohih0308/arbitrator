package com.ohih.arbitrator.security;

import com.ohih.arbitrator.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetailsImpl userDetails = userService.getUserByEmail(email);
        if (userDetails == null) {
            throw new UsernameNotFoundException(email);
        }
        return userDetails; // UserDetailsImpl 객체를 그대로 반환합니다.
    }
}