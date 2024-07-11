package com.ohih.arbitrator.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohih.arbitrator.constant.ResponseCode;
import com.ohih.arbitrator.constant.UrlConstant;
import com.ohih.arbitrator.user.dto.LoginResultDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        LoginResultDto loginResultDto = new LoginResultDto(true,
                ResponseCode.LOGIN_SUCCESS,
                UrlConstant.HOME);

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(loginResultDto));
        response.getWriter().flush();
    }
}