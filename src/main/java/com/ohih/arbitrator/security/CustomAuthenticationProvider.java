package com.ohih.arbitrator.security;

import com.ohih.arbitrator.constant.ResponseCode;
import com.ohih.arbitrator.exception.custom_exception.EmailNotExistException;
import com.ohih.arbitrator.exception.custom_exception.IncorrectCredentialException;
import com.ohih.arbitrator.exception.custom_exception.ValidationException;
import com.ohih.arbitrator.user.validation.UserValidator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    private final UserValidator userValidator;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    // 생성자에서 UserDetailsService와 PasswordEncoder를 설정합니다.
    public CustomAuthenticationProvider(UserValidator userValidator, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userValidator = userValidator;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(passwordEncoder);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<Integer> errorCodes = userValidator.validateLoginInput(email, password);
        if (!errorCodes.isEmpty()) {
            throw new ValidationException(errorCodes);
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new EmailNotExistException(ResponseCode.LOGIN_FAILURE);
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new IncorrectCredentialException(ResponseCode.LOGIN_FAILURE);
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}