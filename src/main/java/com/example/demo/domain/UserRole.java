package com.example.demo.domain;

import org.springframework.security.core.GrantedAuthority;

/*
 * Перечесление групп доступа пользователей
 */
public enum  UserRole implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
