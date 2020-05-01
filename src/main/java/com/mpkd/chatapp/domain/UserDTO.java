package com.mpkd.chatapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO {

    protected final String email;

    UserDTO(User user) {
        this.email = user.getEmail();
    }

}
