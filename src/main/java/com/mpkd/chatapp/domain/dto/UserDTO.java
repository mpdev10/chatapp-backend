package com.mpkd.chatapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class UserDTO {

    private String name;
    private String password;
    private String email;

}

