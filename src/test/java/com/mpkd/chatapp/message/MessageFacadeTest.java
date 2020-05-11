package com.mpkd.chatapp.message;

import com.mpkd.chatapp.common.ResourceNotFoundException;
import com.mpkd.chatapp.message.dto.ReceivedMessageDTO;
import com.mpkd.chatapp.message.dto.SentMessageDTO;
import com.mpkd.chatapp.user.UserFacade;
import com.mpkd.chatapp.user.dto.UserDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageFacadeTest {


    private static final String SENDER = "sender";
    private static final String RECEIVER = "receiver";
    private static final String NOT_EXISTING_USER = "unknown";
    private static final UserFacade userFacade = mock(UserFacade.class);

    @BeforeAll
    static void setUp() {
        when(userFacade.userExists(ArgumentMatchers.matches("(" + SENDER + "|" + RECEIVER + ")"))).thenReturn(true);
        when(userFacade.getUser(SENDER)).thenReturn(UserDTO.builder().name(SENDER).build());
        when(userFacade.getUser(RECEIVER)).thenReturn(UserDTO.builder().name(RECEIVER).build());
    }

    private static SentMessageDTO newSentMessage(String receiverName) {
        return new SentMessageDTO(receiverName, "content");
    }

    @Test
    void sendMessage_senderDoesntExist_throwsException() {
        var messageFacade = new MessageConfiguration().messageFacade(userFacade);
        assertThrows(ResourceNotFoundException.class, () -> messageFacade.sendMessage(NOT_EXISTING_USER, newSentMessage(RECEIVER)));
    }

    @Test
    void sendMessage_receiverDoesntExist_throwsException() {
        var messageFacade = new MessageConfiguration().messageFacade(userFacade);
        assertThrows(ResourceNotFoundException.class, () -> messageFacade.sendMessage(SENDER, newSentMessage(NOT_EXISTING_USER)));
    }

    @Test
    void sendMessage_senderAndReceiverExist_messageIsPresent() {
        var messageFacade = new MessageConfiguration().messageFacade(userFacade);
        var sentMessage = newSentMessage(RECEIVER);
        var expectedReceivedMessage = new ReceivedMessageDTO(SENDER, sentMessage.getContent());
        messageFacade.sendMessage(SENDER, sentMessage);
        var receivedMessages = messageFacade.getReceivedMessages(RECEIVER, PageRequest.of(0, 1));
        var sentMessages = messageFacade.getSentMessages(SENDER, PageRequest.of(0, 1));
        assertAll(
                () -> assertThat(receivedMessages).contains(expectedReceivedMessage),
                () -> assertThat(sentMessages).contains(sentMessage)
        );
    }

}
