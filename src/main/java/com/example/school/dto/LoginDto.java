package com.example.school.dto;

import lombok.*;
/**
 * �⺻ �α��� dto
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {
    private String userId;                       //userID
    private String role;                         //role
}
