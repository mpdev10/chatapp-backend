package com.mpkd.chatapp.user.validation;

import com.mpkd.chatapp.user.UserDTO;

public interface IUserValidationService {
    UserValidationResult validate(UserDTO userDTO);
}
