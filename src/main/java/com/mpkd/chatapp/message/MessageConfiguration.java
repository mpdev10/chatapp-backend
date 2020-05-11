package com.mpkd.chatapp.message;

import com.mpkd.chatapp.user.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MessageConfiguration {

    MessageFacade messageFacade(UserFacade userFacade) {
        return new MessageFacadeImpl(userFacade, new InMemoryMessageRepository());
    }

    @Bean
    MessageFacade messageFacade(UserFacade userFacade, MessageRepository messageRepository) {
        return new MessageFacadeImpl(userFacade, messageRepository);
    }

}
