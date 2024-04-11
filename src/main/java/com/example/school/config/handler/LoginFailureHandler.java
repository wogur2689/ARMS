package com.example.school.config.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * �α��� ���н� �α��� �������� ���� �޼��� ����.
 */
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.info("### error msg : {} ###", exception.getMessage());

        //�α��� ȭ������ �̵�
        log.info("### login fail ###");
        response.sendRedirect("/login");
    }
}
