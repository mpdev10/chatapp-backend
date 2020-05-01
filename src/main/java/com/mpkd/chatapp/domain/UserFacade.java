package com.mpkd.chatapp.domain;

public interface UserFacade {

    void postUser(UserDTO user);

    UserDTO getUser(String email);

}
