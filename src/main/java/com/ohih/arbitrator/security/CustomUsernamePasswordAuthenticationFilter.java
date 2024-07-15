package com.ohih.arbitrator.security;

import com.ohih.arbitrator.constant.UrlConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.io.IOException;
import java.util.Map;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomUsernamePasswordAuthenticationFilter.class);

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.setAuthenticationManager(authenticationManager);
        this.setFilterProcessesUrl(UrlConstant.PROCESS_LOGIN); // 로그인 URL 설정
        this.setAuthenticationSuccessHandler(new AuthenticationSuccessHandlerImpl()); // 성공 핸들러 설정
        this.setAuthenticationFailureHandler(new AuthenticationFailureHandlerImpl()); // 실패 핸들러 설정
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = null;
        String password = null;

        try {
            // 요청에서 이메일과 비밀번호를 읽어옴
            Map<String, String> loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            email = loginData.get("email");
            password = loginData.get("password");
        } catch (IOException e) {
            logger.error("Error reading login data", e);
        }

        // 이메일과 비밀번호로 인증 토큰 생성
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);
        setDetails(request, authRequest);
        // AuthenticationManager를 통해 인증 시도
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 인증 결과를 SecurityContext에 설정
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        // 세션에 SecurityContext 저장
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

        // 부모 클래스의 성공 처리 메서드 호출
        super.successfulAuthentication(request, response, chain, authResult);
    }
}