package com.mpkd.chatapp.message;

import com.mpkd.chatapp.message.dto.ReceivedMessageDTO;
import com.mpkd.chatapp.message.dto.SentMessageDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MessageMapper {

    static SentMessageDTO toSentMessageDTO(Message message) {
        return new SentMessageDTO(message.getReceiverUsername(), message.getContent());
    }

    static ReceivedMessageDTO toReceivedMessageDTO(Message message) {
        return new ReceivedMessageDTO(message.getSenderUsername(), message.getContent());
    }

}
