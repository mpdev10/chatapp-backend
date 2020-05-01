package com.mpkd.chatapp.user.validation;

import com.mpkd.chatapp.user.UserDTO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.mpkd.chatapp.user.validation.UserValidationResult.ErrorCode.*;
import static java.util.Objects.isNull;

@Service
public class UserValidationService implements IUserValidationService {

    private final UserValidationConfig config;

    @Autowired
    public UserValidationService(UserValidationConfig config) {
        this.config = config;
    }


    @Override
    public UserValidationResult validate(UserDTO userDTO) {
        UserValidationResult validationResult = UserValidationResult.newInstance();
        checkEmailErrors(userDTO.getEmail()).ifPresent(validationResult::addErrorCode);
        checkPasswordErrors(userDTO.getPassword()).ifPresent(validationResult::addErrorCode);
        return validationResult;
    }

    private Optional<UserValidationResult.ErrorCode> checkEmailErrors(String email) {
        UserValidationResult.ErrorCode errorCode = null;
        if (isNull(email)) {
            errorCode = NULL_EMAIL;
        } else if (!EmailValidator.getInstance().isValid(email)) {
            errorCode = INVALID_EMAIL;
        }
        return Optional.ofNullable(errorCode);
    }

    private Optional<UserValidationResult.ErrorCode> checkPasswordErrors(String password) {
        UserValidationResult.ErrorCode errorCode = null;
        if (isNull(password)) {
            errorCode = NULL_PASSWORD;
        } else if (password.length() < config.getMinPasswordLength()) {
            errorCode = TOO_SHORT_PASSWORD;
        }
        return Optional.ofNullable(errorCode);
    }

}
