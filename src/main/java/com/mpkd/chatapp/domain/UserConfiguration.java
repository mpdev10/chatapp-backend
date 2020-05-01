package com.mpkd.chatapp.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    UserFacade userFacade() {
        return new UserFacadeImpl(new InMemoryUserRepository());
    }

    @Bean
    UserFacade userFacade(UserRepository userRepository) {
        return new UserFacadeImpl(userRepository);
    }

}
