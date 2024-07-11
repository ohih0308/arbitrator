package com.ohih.arbitrator.common;

import com.ohih.arbitrator.constant.UrlConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class ArbitratorRestController {
    private final ArbitratorService arbitratorService;

    @GetMapping(UrlConstant.GET_MESSAGE)
    public String getMessage(@RequestParam("responseCode") int responseCode) {
        return arbitratorService.getMessage(responseCode);
    }
}
