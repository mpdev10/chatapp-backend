package com.mpkd.chatapp.message;

import com.mpkd.chatapp.common.ResourceNotFoundException;
import com.mpkd.chatapp.message.dto.ReceivedMessageDTO;
import com.mpkd.chatapp.message.dto.SentMessageDTO;
import com.mpkd.chatapp.user.UserFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

class MessageFacadeImpl implements MessageFacade {

    private final UserFacade userFacade;
    private final MessageRepository messageRepository;

    MessageFacadeImpl(UserFacade userFacade, MessageRepository messageRepository) {
        this.userFacade = userFacade;
        this.messageRepository = messageRepository;
    }

    @Override
    public void sendMessage(String senderUsername, SentMessageDTO message) {
        if (!userFacade.userExists(senderUsername)) {
            throw new ResourceNotFoundException("User", "name", senderUsername);
        }
        if (!userFacade.userExists(message.getReceiverUsername())) {
            throw new ResourceNotFoundException("User", "name", message.getReceiverUsername());
        }
        messageRepository.save(Message.builder()
                .senderUsername(senderUsername)
                .receiverUsername(message.getReceiverUsername())
                .content(message.getContent())
                .build());
    }

    @Override
    public Page<ReceivedMessageDTO> getReceivedMessages(String receiverUsername, Pageable pageable) {
        return messageRepository.findAllByReceiverUsername(receiverUsername, pageable).map(MessageMapper::toReceivedMessageDTO);
    }

    @Override
    public Page<SentMessageDTO> getSentMessages(String senderUsername, Pageable pageable) {
        return messageRepository.findAllBySenderUsername(senderUsername, pageable).map(MessageMapper::toSentMessageDTO);
    }


}
