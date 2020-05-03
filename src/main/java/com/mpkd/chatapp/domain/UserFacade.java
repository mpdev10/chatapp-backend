package com.mpkd.chatapp.domain;

import com.mpkd.chatapp.domain.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserFacade extends UserDetailsService {

    void postUser(UserDTO user);

    UserDTO getUser(String email);

}
