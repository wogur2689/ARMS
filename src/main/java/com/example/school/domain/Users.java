package com.example.school.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * 유저 테이블
 */
@Getter
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;    //사용자명

    @Column(name = "email")
    private String email;    //이메일

    @Column(name = "password")
    private String password;    //비밀번호
}
