package com.mpkd.chatapp.user.validation;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.mpkd.chatapp.user.validation.UserValidationResult.ErrorCode.NULL_EMAIL;
import static com.mpkd.chatapp.user.validation.UserValidationResult.ErrorCode.TOO_SHORT_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;

class UserValidationResultTest {

    @Test
    void isUserValid_noErrorCodes_returnsTrue() {
        UserValidationResult emptyResult = UserValidationResult.newInstance();
        assertThat(emptyResult.isUserValid()).isTrue();
    }

    @Test
    void addErrorCode_errorCodesAdded_codesAreAddedAndUserIsInvalid() {
        UserValidationResult result = UserValidationResult.newInstance();
        Set<UserValidationResult.ErrorCode> errorCodes = Set.of(TOO_SHORT_PASSWORD, NULL_EMAIL);
        errorCodes.forEach(result::addErrorCode);
        assertThat(result.getErrorCodes()).containsAll(errorCodes);
        assertThat(result.isUserValid()).isFalse();
    }

}