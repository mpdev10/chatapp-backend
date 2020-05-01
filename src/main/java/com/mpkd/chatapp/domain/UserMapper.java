package com.mpkd.chatapp.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserMapper {

    static UserDTO toDTO(User user) {
        return UserDTO.newInstanceEncryptedPassword(user.getEmail(), user.getPassword());
    }
}
