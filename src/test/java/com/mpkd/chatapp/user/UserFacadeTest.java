package com.mpkd.chatapp.user;

import com.mpkd.chatapp.common.ErrorCode;
import com.mpkd.chatapp.user.dto.UserDTO;
import com.mpkd.chatapp.user.exception.InvalidUserDataException;
import com.mpkd.chatapp.user.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserFacadeTest {

    private static final String INVALID_EMAIL = "invalid@..s";
    private static final String VALID_EMAIL = "valid@mail.com";
    private static final String VALID_NAME = "username";
    private static final String VALID_PASSWORD = "password";


    private static void postUser(String name, String email, String password) {
        var userFacade = new UserConfiguration().userFacade();
        var user = UserDTO.builder().name(name).email(email).password(password).build();
        userFacade.postUser(user);
    }

    @Test
    void postUser_nullNameNullNullEmailNullPassword_throwsExceptionWithErrorCodes() {
        var exception = assertThrows(InvalidUserDataException.class, () -> postUser(null, null, null));
        assertAll(
                () -> assertThat(exception.getErrorCodes()).containsOnly(
                        ErrorCode.NULL_NAME, ErrorCode.NULL_EMAIL, ErrorCode.NULL_PASSWORD),
                () -> assertThat(exception.getMessage()).contains(
                        ErrorCode.NULL_NAME.getMessage(), ErrorCode.NULL_PASSWORD.getMessage(), ErrorCode.NULL_EMAIL.getMessage())
        );
    }

    @Test
    void postUser_nullEmailNullPassword_throwsExceptionWithErrorCodes() {
        var exception = assertThrows(InvalidUserDataException.class, () -> postUser(VALID_NAME, null, null));
        assertAll(
                () -> assertThat(exception.getErrorCodes()).containsOnly(ErrorCode.NULL_EMAIL, ErrorCode.NULL_PASSWORD),
                () -> assertThat(exception.getMessage()).contains(ErrorCode.NULL_EMAIL.getMessage(), ErrorCode.NULL_PASSWORD.getMessage())
        );
    }

    @Test
    void postUser_invalidEmailNullPassword_throwsExceptionWithErrorCodes() {
        var exception = assertThrows(InvalidUserDataException.class, () -> postUser(VALID_NAME, INVALID_EMAIL, null));
        assertAll(
                () -> assertThat(exception.getErrorCodes()).containsOnly(ErrorCode.INVALID_EMAIL, ErrorCode.NULL_PASSWORD),
                () -> assertThat(exception.getMessage()).contains(ErrorCode.INVALID_EMAIL.getMessage(), ErrorCode.NULL_PASSWORD.getMessage())
        );
    }

    @Test
    void postUser_validEmailNullPassword_throwsExceptionWithErrorCode() {
        var exception = assertThrows(InvalidUserDataException.class, () -> postUser(VALID_NAME, VALID_EMAIL, null));
        assertAll(
                () -> assertThat(exception.getErrorCodes()).containsOnly(ErrorCode.NULL_PASSWORD),
                () -> assertThat(exception.getMessage()).contains(ErrorCode.NULL_PASSWORD.getMessage())
        );
    }

    @Test
    void postUser_nullEmailValidPassword_throwsExceptionWithErrorCode() {
        var exception = assertThrows(InvalidUserDataException.class, () -> postUser(VALID_NAME, null, VALID_PASSWORD));
        assertAll(
                () -> assertThat(exception.getErrorCodes()).containsOnly(ErrorCode.NULL_EMAIL),
                () -> assertThat(exception.getMessage()).contains(ErrorCode.NULL_EMAIL.getMessage())
        );
    }

    @Test
    void postUser_invalidEmailValidPassword_throwsExceptionWithErrorCode() {
        var exception = assertThrows(InvalidUserDataException.class, () -> postUser(VALID_NAME, INVALID_EMAIL, VALID_PASSWORD));
        assertAll(
                () -> assertThat(exception.getErrorCodes()).containsOnly(ErrorCode.INVALID_EMAIL),
                () -> assertThat(exception.getMessage()).contains(ErrorCode.INVALID_EMAIL.getMessage())
        );
    }

    @Test
    void postUser_validNameEmailPassword_userIsSaved() {
        var userFacade = new UserConfiguration().userFacade();
        var user = UserDTO.builder().name(VALID_NAME).password(VALID_PASSWORD).email(VALID_EMAIL).build();
        userFacade.postUser(user);
        var fetchedUser = userFacade.getUser(user.getName());
        assertAll(
                () -> assertThat(fetchedUser.getEmail()).isEqualTo(user.getEmail()),
                () -> assertThat(fetchedUser.getName()).isEqualTo(user.getName())
        );
    }

    @Test
    void postUser_userWithEqualNameAlreadyExists_throwsException() {
        var userFacade = new UserConfiguration().userFacade();
        var user = UserDTO.builder().name(VALID_NAME).password(VALID_PASSWORD).email(VALID_EMAIL).build();
        userFacade.postUser(user);
        assertThrows(UserAlreadyExistsException.class, () -> userFacade.postUser(user));
    }

    @Test
    void loadByUserName_userDoesntExist_throwsException() {
        var userFacade = new UserConfiguration().userFacade();
        assertThrows(UsernameNotFoundException.class, () -> userFacade.loadUserByUsername(VALID_NAME));
    }

    @Test
    void loadByUserName_userExists_userDetailsAreValid() {
        var userFacade = new UserConfiguration().userFacade();
        var user = UserDTO.builder().name(VALID_NAME).password(VALID_PASSWORD).email(VALID_EMAIL).build();
        userFacade.postUser(user);
        var userDetails = userFacade.loadUserByUsername(user.getName());
        assertThat(userDetails.getUsername()).isEqualTo(user.getName());
    }

}