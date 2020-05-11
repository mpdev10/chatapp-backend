package com.mpkd.chatapp.user;

import com.google.common.collect.Sets;
import com.mpkd.chatapp.common.ErrorCode;
import com.mpkd.chatapp.common.ResourceNotFoundException;
import com.mpkd.chatapp.user.dto.UserDTO;
import com.mpkd.chatapp.user.exception.InvalidUserDataException;
import com.mpkd.chatapp.user.exception.UserAlreadyExistsException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static com.mpkd.chatapp.common.ErrorCode.*;
import static java.util.Objects.isNull;

class UserFacadeImpl implements UserFacade {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    UserFacadeImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private static Optional<ErrorCode> checkNameErrors(String name) {
        return isNull(name) ? Optional.of(NULL_NAME) : Optional.empty();
    }

    private static Optional<ErrorCode> checkEmailErrors(String email) {
        if (isNull(email)) {
            return Optional.of(NULL_EMAIL);
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            return Optional.of(INVALID_EMAIL);
        }
        return Optional.empty();
    }

    private static Optional<ErrorCode> checkPasswordErrors(String password) {
        return isNull(password) ? Optional.of(NULL_PASSWORD) : Optional.empty();
    }

    @Override
    public void postUser(UserDTO user) {
        Set<ErrorCode> errors = Sets.newHashSet();
        checkNameErrors(user.getName()).ifPresent(errors::add);
        checkEmailErrors(user.getEmail()).ifPresent(errors::add);
        checkPasswordErrors(user.getPassword()).ifPresent(errors::add);
        if (!errors.isEmpty()) {
            throw new InvalidUserDataException(errors);
        }
        if (userRepository.existsByName(user.getName())) {
            throw new UserAlreadyExistsException(user.getName());
        }
        userRepository.save(User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .name(user.getName())
                .build()
        );
    }

    @Override
    public UserDTO getUser(String email) {
        return userRepository.findByName(email).map(UserMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    public boolean userExists(String name) {
        return userRepository.existsByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        return userRepository.findByName(name).map(UserMapper::toDetails)
                .orElseThrow(() -> new UsernameNotFoundException(name));
    }
}
