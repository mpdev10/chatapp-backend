package com.mpkd.chatapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {

    private String email;
    private String password;
    private String encryptedPassword;

    public UserDTO(String email, String password, String encryptedPassword) {
        this.email = email;
        this.password = password;
        this.encryptedPassword = encryptedPassword;
    }

    public static UserDTO newInstancePlaintextPassword(String email, String password) {
        return new UserDTO(email, password, null);
    }

    public static UserDTO newInstanceEncryptedPassword(String email, String encryptedPassword) {
        return new UserDTO(email, null, encryptedPassword);
    }
}
