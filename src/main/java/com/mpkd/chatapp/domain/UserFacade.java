package com.mpkd.chatapp.domain;

public interface UserFacade {

    void postUser(CreateUserDTO user);

    UserDTO getUser(String email);

}
