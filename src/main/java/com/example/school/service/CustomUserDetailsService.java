package com.example.school.service;

import com.example.school.domain.Role;
import com.example.school.domain.Users;
import com.example.school.dto.CustomUserDetailsDto;
import com.example.school.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Users> userInfo = Optional.ofNullable(loginRepository.findByUsername(userId)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저입니다.")));


        Collection<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(Role.USER.getRole()));

        return CustomUserDetailsDto.builder()
                .username(userInfo.get().getUsername())
                .password(userInfo.get().getPassword())
                .authorities(authority)
                .build();
    }
}
