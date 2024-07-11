package com.ohih.arbitrator.security;

import com.ohih.arbitrator.constant.UrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CustomUsernamePasswordAuthenticationFilter customFilter = new CustomUsernamePasswordAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)));

        http
                // CSRF 설정: 쿠키를 통해 CSRF 토큰 저장
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

                // 세션 관리 설정: 필요시 세션 생성
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

                // 요청 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(UrlConstant.REGISTER, UrlConstant.LOGIN).anonymous() // 로그인 및 회원가입 요청은 인증되지 않은 사용자만 허용
                        .requestMatchers(UrlConstant.CALENDAR).authenticated() // /calendar 요청은 인증된 사용자만 허용
                        .anyRequest().permitAll() // 그 외 모든 요청은 허용
                )

                // 로그인 설정
                .formLogin(form -> form
                                .loginPage(UrlConstant.LOGIN) // 로그인 페이지 설정
        /*.loginProcessingUrl(UrlConstant.PROCESS_LOGIN)
        .successHandler(new AuthenticationSuccessHandlerImpl())
        .failureHandler(new AuthenticationFailureHandlerImpl())*/
                )

                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl(UrlConstant.PROCESS_LOGOUT) // 로그아웃 요청 URL
                        .logoutSuccessUrl(UrlConstant.HOME) // 로그아웃 성공 후 리다이렉트 URL
                        .deleteCookies("JSESSIONID") // 로그아웃 후 JSESSIONID 쿠키 삭제
                        .invalidateHttpSession(true) // 세션 무효화
                )

                // 커스텀 필터 추가
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}