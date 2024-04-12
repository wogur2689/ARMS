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
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Securtity
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(request ->
                 request
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        );
        http.formLogin(login ->
                login
                        .loginPage("/login")
                        .loginProcessingUrl("/login-processing")
                        .defaultSuccessUrl("/")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(new LoginSuccessHandler())
                        .failureHandler(new LoginFailureHandler())
                        .permitAll()
        );
        http.logout(logout ->
                logout
                        .deleteCookies("JSESSIONID","remember-me")
                        .addLogoutHandler(new LogoutCustomHandler())
                        .permitAll()
        );
        http.authenticationProvider(new LoginProvider(customUserDetailsService, getPasswordEncoder()));
        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
