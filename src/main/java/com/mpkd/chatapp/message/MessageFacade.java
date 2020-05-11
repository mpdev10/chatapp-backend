package com.mpkd.chatapp.message;

import com.mpkd.chatapp.message.dto.ReceivedMessageDTO;
import com.mpkd.chatapp.message.dto.SentMessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageFacade {

    void sendMessage(String senderUsername, SentMessageDTO message);

    Page<SentMessageDTO> getSentMessages(String senderUsername, Pageable pageable);

    Page<ReceivedMessageDTO> getReceivedMessages(String receiverUsername, Pageable pageable);
}
