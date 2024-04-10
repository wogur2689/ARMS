package com.example.school.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 유저 권한 테이블
 */
@Getter
@Entity
@Table(name = "authorities")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;    //사용자명

    @Column(name = "authority")
    private String authority;    //권한
}
