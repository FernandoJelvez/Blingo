package com.blingo.lingdyo.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "customLogs")
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws java.io.IOException {

        String username = authentication.getName();
        String ip = request.getRemoteAddr();

        log.info("configuration.LoginSuccessHandler - LOGIN SUCCESS: User '{}' logged in from IP {}", username, ip);

        response.sendRedirect("/my/home");
    }
}