package com.ohih.arbitrator.user;

import com.ohih.arbitrator.constant.ResponseCode;
import com.ohih.arbitrator.constant.UrlConstant;
import com.ohih.arbitrator.security.UserDetailsImpl;
import com.ohih.arbitrator.user.dto.RegisterDto;
import com.ohih.arbitrator.user.dto.RegisterResultDto;
import com.ohih.arbitrator.user.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;
    private final UserMapper mapper;

    public UserDetailsImpl getUserByEmail(String email) {
        return mapper.getUserByEmail(email);
    }

    private int isEmailDuplicated(String email) {
        return mapper.isEmailDuplicate(email);
    }

    public RegisterResultDto register(RegisterDto registerDto) {
        RegisterResultDto registerResultDto = new RegisterResultDto();
        List<Integer> responseCodes = userValidator.validateRegisterDto(registerDto);

        if (!responseCodes.isEmpty()) {
            registerResultDto.setSuccess(false);
            registerResultDto.setResponseCodes(responseCodes);
            return registerResultDto;
        }

        if (isEmailDuplicated(registerDto.getEmail()) > 0) {
            responseCodes.add(ResponseCode.EMAIL_DUPLICATION_ERROR);
            registerResultDto.setSuccess(false);
            registerResultDto.setResponseCodes(responseCodes);
            return registerResultDto;
        }

        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        mapper.register(registerDto);

        registerResultDto.setSuccess(true);
        registerResultDto.setRedirectUrl(UrlConstant.LOGIN);
        responseCodes.add(ResponseCode.REGISTER_SUCCESS);
        registerResultDto.setResponseCodes(responseCodes);
        return registerResultDto;
    }
}