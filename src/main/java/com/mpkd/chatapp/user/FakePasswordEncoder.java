package com.mpkd.chatapp.user;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

class FakePasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Objects.equals(rawPassword.toString(), encodedPassword);
    }
}
