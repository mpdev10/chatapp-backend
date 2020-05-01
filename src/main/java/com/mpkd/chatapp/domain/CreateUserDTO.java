package com.mpkd.chatapp.domain;

import lombok.Getter;

@Getter
public class CreateUserDTO extends UserDTO {
    private final String password;

    public CreateUserDTO(String email, String password) {
        super(email);
        this.password = password;
    }

}
