package com.ohih.arbitrator.user;

import com.ohih.arbitrator.constant.UrlConstant;
import com.ohih.arbitrator.user.dto.RegisterDto;
import com.ohih.arbitrator.user.dto.RegisterResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping(UrlConstant.PERFORM_REGISTER)
    public RegisterResultDto register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }
}
