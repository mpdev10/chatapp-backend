package com.mpkd.chatapp.user.validation;

import com.mpkd.chatapp.user.UserDTO;
import org.junit.jupiter.api.Test;

import static com.mpkd.chatapp.user.validation.UserValidationResult.ErrorCode.*;
import static com.mpkd.chatapp.user.validation.UserValidationResultAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserValidationServiceTest {


    private static final UserValidationConfig config = new UserValidationConfig(5);
    private static final String VALID_MAIL_ADDR = "valid@mail.to";
    private static final String INVALID_MAIL_ADDR = "invalid@...";
    private static final String VALID_PASSWORD = "validpassword12";
    private static final String INVALID_PASSWORD = "ab";

    private static UserValidationResult validateUser(String userEmail, String userPassword) {
        var validationService = new UserValidationService(config);
        return validationService.validate(new UserDTO(userEmail, userPassword));
    }

    @Test
    void validate_validData_noErrorCodesAndValidUser() {
        var validationResult = validateUser(VALID_MAIL_ADDR, VALID_PASSWORD);
        assertAll(
                () -> assertThat(validationResult).hasNoErrorCodes(),
                () -> assertThat(validationResult).userIsValid()
        );
    }

    @Test
    void validate_noEmail_nullEmailErrorCodeAndInvalidUser() {
        var validationResult = validateUser(null, VALID_PASSWORD);
        assertAll(
                () -> assertThat(validationResult).hasErrorCode(NULL_EMAIL),
                () -> assertThat(validationResult).userIsInvalid()
        );
    }

    @Test
    void validate_invalidEmail_invalidEmailErrorCodeAndInvalidUser() {
        var validationResult = validateUser(INVALID_MAIL_ADDR, VALID_PASSWORD);
        assertAll(
                () -> assertThat(validationResult).hasErrorCode(INVALID_EMAIL),
                () -> assertThat(validationResult).userIsInvalid()
        );
    }

    @Test
    void validate_noPassword_nullPasswordErrorCodeAndInvalidUser() {
        var validationResult = validateUser(VALID_MAIL_ADDR, null);
        assertAll(
                () -> assertThat(validationResult).hasErrorCode(NULL_PASSWORD),
                () -> assertThat(validationResult).userIsInvalid()
        );
    }

    @Test
    void validate_passwordTooShort_passwordTooShortErrorCodeAndInvalidUser() {
        var validationResult = validateUser(VALID_MAIL_ADDR, INVALID_PASSWORD);
        assertAll(
                () -> assertThat(validationResult).hasErrorCode(TOO_SHORT_PASSWORD),
                () -> assertThat(validationResult).userIsInvalid()
        );
    }

    @Test
    void validate_nullEmailNullPassword_nullEmailNullPasswordErrorCodesAndInvalidUser() {
        var validationResult = validateUser(null, null);
        assertAll(
                () -> assertThat(validationResult).hasErrorCodes(NULL_EMAIL, NULL_PASSWORD),
                () -> assertThat(validationResult).userIsInvalid()
        );
    }

    @Test
    void validate_invalidEmailNullPassword_invalidEmailNullPasswordErrorCodesAndInvalidUser() {
        var validationResult = validateUser(INVALID_MAIL_ADDR, null);
        assertAll(
                () -> assertThat(validationResult).hasErrorCodes(INVALID_EMAIL, NULL_PASSWORD),
                () -> assertThat(validationResult).userIsInvalid()
        );
    }

    @Test
    void validate_nullEmailTooShortPassword_nullEmailTooShortPasswordErrorCodesAndInvalidUser() {
        var validationResult = validateUser(null, INVALID_PASSWORD);
        assertAll(
                () -> assertThat(validationResult).hasErrorCodes(NULL_EMAIL, TOO_SHORT_PASSWORD),
                () -> assertThat(validationResult).userIsInvalid()
        );
    }

    @Test
    void validate_invalidEmailTooShortPassword_invalidEmailTooShortPasswordErrorCodesAndInvalidUser() {
        var validationResult = validateUser(INVALID_MAIL_ADDR, INVALID_PASSWORD);
        assertAll(
                () -> assertThat(validationResult).hasErrorCodes(INVALID_EMAIL, TOO_SHORT_PASSWORD),
                () -> assertThat(validationResult).userIsInvalid()
        );
    }
}