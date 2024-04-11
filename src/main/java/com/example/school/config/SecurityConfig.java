package com.example.school.config;

import com.example.school.config.handler.LoginFailureHandler;
import com.example.school.config.handler.LoginSuccessHandler;
import com.example.school.config.handler.LogoutCustomHandler;
import com.example.school.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //�� ���� Ȱ��ȭ
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Securtity ����
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //1�ܰ� ���� �˻�
        http.csrf(AbstractHttpConfigurer::disable); //csrf��ȣ(������ ������)
        http.authorizeHttpRequests(request ->
                 request
                        .requestMatchers("/**")//������ ���� ��� ��� ����
                        .permitAll() //�ش� ��δ� ���Ȱ˻� ����.
                        .anyRequest()
                        .authenticated() //������ ��� ���Ȱ˻�
        );
        //2�ܰ� �α��� �� ����
        http.formLogin(login ->
                login
                        .loginPage("/login") //����� ���� �α��� ������
                        .loginProcessingUrl("/login-processing") //�α��� form action Url
                        .defaultSuccessUrl("/")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(new LoginSuccessHandler()) //�α��� ���� �ڵ鷯
                        .failureHandler(new LoginFailureHandler()) //�α��� ���� �ڵ鷯
                        .permitAll() //�α��� ������ ���� ���� ����
        );
        //3�ܰ� �α׾ƿ� ����
        http.logout(logout ->
                logout
                        .deleteCookies("JSESSIONID","remember-me") //�α׾ƿ��� ��Ű ����
                        .addLogoutHandler(new LogoutCustomHandler()) //�α׾ƿ� �ڵ鷯
                        .permitAll()
        );
        //4�ܰ� ���� ����
        http.authenticationProvider(new LoginProvider(customUserDetailsService, getPasswordEncoder()));
        return http.build();
    }

    /**
     * �н����� ���ڴ�
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
