package com.ohih.arbitrator.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohih.arbitrator.constant.ResponseCode;
import com.ohih.arbitrator.constant.UrlConstant;
import com.ohih.arbitrator.user.dto.LoginResultDto;
import com.ohih.arbitrator.user.dto.ResultDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFailureHandlerImpl.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        LoginResultDto loginResultDto = new LoginResultDto(false,
                ResponseCode.LOGIN_FAILURE,
                UrlConstant.LOGIN);

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(loginResultDto));
        response.getWriter().flush();
    }
}