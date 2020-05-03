package com.mpkd.chatapp.domain;

import com.mpkd.chatapp.domain.dto.UserDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserMapper {

    static UserDTO toDTO(User user) {
        return UserDTO.newInstanceEncryptedPassword(user.getEmail(), user.getPassword());
    }

    static UserDetails toDetails(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .roles("USER")
                .build();

    }


}
