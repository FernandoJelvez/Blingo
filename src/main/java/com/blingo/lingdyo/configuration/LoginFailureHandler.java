package com.blingo.lingdyo.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "customLogs")
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws java.io.IOException {

        String username = request.getParameter("username");
        String ip = request.getRemoteAddr();

        log.warn("configuration.LoginFailureHandler - LOGIN FAILED: User '{}' from IP {}. Reason: {}",
                username, ip, exception.getMessage());

        response.sendRedirect("/login?error");
    }
}