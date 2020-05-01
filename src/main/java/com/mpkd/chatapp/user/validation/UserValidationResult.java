package com.mpkd.chatapp.user.validation;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

public class UserValidationResult {

    @Getter
    private final Set<ErrorCode> errorCodes = Sets.newHashSet();

    private UserValidationResult() {
    }

    public static UserValidationResult newInstance() {
        return new UserValidationResult();
    }

    public void addErrorCode(ErrorCode errorCode) {
        errorCodes.add(errorCode);
    }

    public boolean isUserValid() {
        return errorCodes.isEmpty();
    }

    public enum ErrorCode {
        NULL_EMAIL, NULL_PASSWORD, INVALID_EMAIL, TOO_SHORT_PASSWORD
    }

}
