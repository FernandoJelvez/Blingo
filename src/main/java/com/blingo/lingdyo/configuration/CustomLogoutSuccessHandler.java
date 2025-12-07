package com.blingo.lingdyo.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j(topic = "customLogs")
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws java.io.IOException {

        String username = (authentication != null) ? authentication.getName() : "Unknown";
        String ip = request.getRemoteAddr();

        log.info("configuration.CustomLogoutSuccessHandler - LOGOUT SUCCESS: User '{}' from IP {}", username, ip);

        // Redirigir al index
        response.sendRedirect("/");
    }
}
