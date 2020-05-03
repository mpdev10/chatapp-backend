package com.mpkd.chatapp.domain;

import com.mpkd.chatapp.common.ErrorCode;
import com.mpkd.chatapp.domain.dto.UserDTO;
import com.mpkd.chatapp.domain.exception.InvalidUserDataException;
import com.mpkd.chatapp.domain.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserFacadeTest {

    private static final String INVALID_EMAIL = "invalid@..s";
    private static final String VALID_EMAIL = "valid@mail.com";
    private static final String VALID_PASSWORD = "password";

    private static void postUser(String email, String password) {
        var userFacade = new UserConfiguration().userFacade();
        var user = UserDTO.newInstancePlaintextPassword(email, password);
        userFacade.postUser(user);
    }

    @Test
    void postUser_nullEmailNullPassword_throwsExceptionWithErrorCodes() {
        var exception = assertThrows(InvalidUserDataException.class, () -> postUser(null, null));
        assertThat(exception.getErrorCodes()).containsOnly(ErrorCode.NULL_EMAIL, ErrorCode.NULL_PASSWORD);
    }

    @Test
    void postUser_invalidEmailNullPassword_throwsExceptionWithErrorCodes() {
        var exception = assertThrows(InvalidUserDataException.class, () -> postUser(INVALID_EMAIL, null));
        assertThat(exception.getErrorCodes()).containsOnly(ErrorCode.INVALID_EMAIL, ErrorCode.NULL_PASSWORD);
    }

    @Test
    void postUser_validEmailNullPassword_throwsExceptionWithErrorCode() {
        var exception = assertThrows(InvalidUserDataException.class, () -> postUser(VALID_EMAIL, null));
        assertThat(exception.getErrorCodes()).containsOnly(ErrorCode.NULL_PASSWORD);
    }

    @Test
    void postUser_nullEmailValidPassword_throwsExceptionWithErrorCode() {
        var exception = assertThrows(InvalidUserDataException.class, () -> postUser(null, VALID_PASSWORD));
        assertThat(exception.getErrorCodes()).containsOnly(ErrorCode.NULL_EMAIL);
    }

    @Test
    void postUser_invalidEmailValidPassword_throwsExceptionWithErrorCode() {
        var exception = assertThrows(InvalidUserDataException.class, () -> postUser(INVALID_EMAIL, VALID_PASSWORD));
        assertThat(exception.getErrorCodes()).containsOnly(ErrorCode.INVALID_EMAIL);
    }

    @Test
    void postUser_validEmailValidPassword_userIsSaved() {
        var userFacade = new UserConfiguration().userFacade();
        var user = UserDTO.newInstancePlaintextPassword(VALID_EMAIL, VALID_PASSWORD);
        userFacade.postUser(user);
        var fetchedUser = userFacade.getUser(user.getEmail());
        assertThat(fetchedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void postUser_userAlreadyExists_throwsException() {
        var userFacade = new UserConfiguration().userFacade();
        var user = UserDTO.newInstancePlaintextPassword(VALID_EMAIL, VALID_PASSWORD);
        userFacade.postUser(user);
        assertThrows(UserAlreadyExistsException.class, () -> userFacade.postUser(user));
    }

    @Test
    void loadByUserName_userDoesntExist_throwsException() {
        var userFacade = new UserConfiguration().userFacade();
        assertThrows(UsernameNotFoundException.class, () -> userFacade.loadUserByUsername(VALID_EMAIL));
    }

    @Test
    void loadByUserName_userExists_userDetailsAreValid() {
        var userFacade = new UserConfiguration().userFacade();
        var user = UserDTO.newInstancePlaintextPassword(VALID_EMAIL, VALID_PASSWORD);
        userFacade.postUser(user);
        var userDetails = userFacade.loadUserByUsername(user.getEmail());
        assertThat(userDetails.getUsername()).isEqualTo(user.getEmail());
    }

}