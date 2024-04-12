package com.example.school.config.handler;

import com.example.school.domain.Role;
import com.example.school.dto.LoginDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.tool.schema.extract.internal.IndexInformationImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        HttpSession session = request.getSession(false);

        try {
            String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            //Collection<GrantedAuthority> authority = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();


            LoginDto loginDTO = LoginDto.builder()
                    .userId(userId)
                    .role(Role.USER.getRole())
                    .build();

            session.setAttribute("UserInfo", loginDTO);
            session.setMaxInactiveInterval(3600);
        } catch (Exception e) {
            log.error("### error : {} ###", e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"시스템 에러입니다.");
        }

        response.sendRedirect(request.getContextPath() + "/");
    }
}
