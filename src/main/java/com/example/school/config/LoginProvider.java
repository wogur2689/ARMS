package com.example.school.config;

import com.example.school.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ���� ��ū ����
 */
@Slf4j
@RequiredArgsConstructor
public class LoginProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //1. �Է¹��� ������ ����� �̸�, ��й�ȣ ����
        //log.info(authentication.getDetails().toString()); //WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=6B8D861708F03350B044E8FE743A227A]
        String username = authentication.getName(); //id
        String password = authentication.getCredentials().toString(); //password

        //2. db���� ����� �̸�, ��й�ȣ ��������
        UserDetails user = customUserDetailsService.loadUserByUsername(username);

        //3. ��й�ȣ�� �´��� Ʋ������ Ȯ��
        if(!passwordEncoder.matches(password, user.getPassword())) {
            log.error("### login param error userId : {}, password : {}", username, password);
            throw new BadCredentialsException("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
        }

        //4. ��ū ����
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
