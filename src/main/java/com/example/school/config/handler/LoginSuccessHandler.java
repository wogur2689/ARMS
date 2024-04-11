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

/**
 * �α��� ������ ���� ����
 */
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        //1. ���� �ʱ�ȭ
        HttpSession session = request.getSession(false);

        try {
            //2. id ����
            String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            //Collection<GrantedAuthority> authority = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

            //3. ���� ���� ����
            LoginDto loginDTO = LoginDto.builder()
                    .userId(userId)
                    .role(Role.USER.getRole())
                    .build();

            //4. �������� ���ǿ� ���� �� ���� ���ӽð� ����(60��)
            session.setAttribute("UserInfo", loginDTO);
            session.setMaxInactiveInterval(3600);
        } catch (Exception e) {
            log.error("### error : {} ###", e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"�ý��� �����Դϴ�. �ٽ� �õ����ּ���.");
        }
        //�α��� ������ �������� �̵�
        response.sendRedirect(request.getContextPath() + "/");
    }
}
