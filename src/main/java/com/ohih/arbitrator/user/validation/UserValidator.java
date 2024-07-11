package com.ohih.arbitrator.user.validation;

import com.ohih.arbitrator.constant.ResponseCode;
import com.ohih.arbitrator.constant.WebConstant;
import com.ohih.arbitrator.user.dto.RegisterDto;
import com.ohih.arbitrator.user.dto.LoginDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.ohih.arbitrator.user.validation.UserValidationPatternConstant.*;

@Component
public class UserValidator {

    public List<Integer> validateRegisterDto(RegisterDto registerDto) {
        List<Integer> errorCodes = new ArrayList<>();

        if (!validateEmail(registerDto.getEmail())) {
            errorCodes.add(ResponseCode.EMAIL_VALIDATION_ERROR);
        }
        if (!validatePassword(registerDto.getPassword())) {
            errorCodes.add(ResponseCode.PASSWORD_VALIDATION_ERROR);
        }
        if (!validateName(registerDto.getName())) {
            errorCodes.add(ResponseCode.USERNAME_VALIDATION_ERROR);
        }

        return errorCodes;
    }

    public List<Integer> validateLoginDto(LoginDto loginDto) {
        List<Integer> errorCodes = new ArrayList<>();

        if (!validateEmail(loginDto.getEmail())) {
            errorCodes.add(ResponseCode.EMAIL_VALIDATION_ERROR);
        }
        if (!validatePassword(loginDto.getPassword())) {
            errorCodes.add(ResponseCode.PASSWORD_VALIDATION_ERROR);
        }

        return errorCodes;
    }

    private boolean validateEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches() && email.endsWith(WebConstant.EMAIL_SUFFIX);
    }

    private boolean validatePassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    private boolean validateName(String name) {
        return name != null && USERNAME_PATTERN.matcher(name).matches();
    }
}