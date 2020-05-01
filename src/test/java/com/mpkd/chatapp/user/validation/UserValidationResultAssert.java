package com.mpkd.chatapp.user.validation;

import org.assertj.core.api.AbstractAssert;

import java.util.Arrays;
import java.util.List;

public class UserValidationResultAssert extends AbstractAssert<UserValidationResultAssert, UserValidationResult> {

    public UserValidationResultAssert(UserValidationResult actual) {
        super(actual, UserValidationResultAssert.class);
    }

    public static UserValidationResultAssert assertThat(UserValidationResult actual) {
        return new UserValidationResultAssert(actual);
    }

    public UserValidationResultAssert hasNoErrorCodes() {
        isNotNull();
        if (!actual.getErrorCodes().isEmpty()) {
            failWithMessage("Expected validation result to contain no error code, but found %s", actual.getErrorCodes());
        }
        return this;
    }

    public UserValidationResultAssert hasErrorCode(UserValidationResult.ErrorCode errorCode) {
        isNotNull();
        if (!actual.getErrorCodes().contains(errorCode)) {
            failWithMessage("Expected validation result to contain %s error code", errorCode);
        }
        return this;
    }

    public UserValidationResultAssert hasErrorCodes(UserValidationResult.ErrorCode... errorCodes) {
        isNotNull();
        List<UserValidationResult.ErrorCode> errorCodeList = Arrays.asList(errorCodes);
        if (!actual.getErrorCodes().containsAll(errorCodeList)) {
            failWithMessage("Expected validation result to contain %s error codes", errorCodeList);
        }
        return this;
    }

    public UserValidationResultAssert userIsValid() {
        isNotNull();
        if (!actual.isUserValid()) {
            failWithMessage("Expected validation result to say user is valid");
        }
        return this;
    }

    public UserValidationResultAssert userIsInvalid() {
        isNotNull();
        if (actual.isUserValid()) {
            failWithMessage("Expected validation result to say user is invalid");
        }
        return this;
    }

}