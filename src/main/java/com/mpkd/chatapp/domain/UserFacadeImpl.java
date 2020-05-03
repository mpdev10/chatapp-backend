package com.mpkd.chatapp.domain;

import com.google.common.collect.Sets;
import com.mpkd.chatapp.common.ErrorCode;
import com.mpkd.chatapp.common.InvalidUserDataException;
import com.mpkd.chatapp.common.ResourceNotFoundException;
import com.mpkd.chatapp.common.UserAlreadyExistsException;
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
        checkEmailErrors(user.getEmail()).ifPresent(errors::add);
        checkPasswordErrors(user.getPassword()).ifPresent(errors::add);
        if (!errors.isEmpty()) {
            throw new InvalidUserDataException(errors);
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }
        userRepository.save(new User(user.getEmail(), passwordEncoder.encode(user.getPassword())));
    }

    @Override
    public UserDTO getUser(String email) {
        return userRepository.findByEmail(email).map(UserMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email).map(UserMapper::toDetails)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
