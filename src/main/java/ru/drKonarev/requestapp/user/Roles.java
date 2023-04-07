package ru.drKonarev.requestapp.user;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    USER, OPERATOR, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
