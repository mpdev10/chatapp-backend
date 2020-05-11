package com.mpkd.chatapp.user;

import com.mpkd.chatapp.user.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserFacade extends UserDetailsService {

    void postUser(UserDTO user);

    UserDTO getUser(String email);

    boolean userExists(String name);
}
