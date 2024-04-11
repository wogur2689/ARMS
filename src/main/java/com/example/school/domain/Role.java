package com.example.school.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ����
 */
@Getter
@AllArgsConstructor
public enum Role {
    USER("ROLE_USER", "����"),
    ADMIN("ROLE_ADMIN", "������");

    private final String role;
    private final String msg;
}
