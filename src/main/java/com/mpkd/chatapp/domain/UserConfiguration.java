package com.mpkd.chatapp.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class UserConfiguration {

    UserFacade userFacade() {
        return new UserFacadeImpl(new InMemoryUserRepository(), new BCryptPasswordEncoder());
    }

    @Bean
    UserFacade userFacade(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserFacadeImpl(userRepository, passwordEncoder);
    }

}
