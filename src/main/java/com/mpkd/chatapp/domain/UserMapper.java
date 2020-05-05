package com.mpkd.chatapp.domain;

import com.mpkd.chatapp.domain.dto.UserDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserMapper {

    static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .encryptedPassword(user.getPassword())
                .build();
    }

    static UserDetails toDetails(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .roles("USER")
                .build();

    }
}
