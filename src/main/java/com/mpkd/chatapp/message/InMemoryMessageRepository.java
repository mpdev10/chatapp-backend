package com.mpkd.chatapp.message;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryMessageRepository implements MessageRepository {

    private final Map<Long, Message> messages = Maps.newConcurrentMap();
    private Long idCounter = 0L;

    @Override
    public Page<Message> findAllByReceiverUsername(String receiverUsername, Pageable pageable) {
        return findByPredicate(message -> Objects.equal(message.getReceiverUsername(), receiverUsername), pageable);
    }

    @Override
    public Page<Message> findAllBySenderUsername(String senderUsername, Pageable pageable) {
        return findByPredicate(message -> Objects.equal(message.getSenderUsername(), senderUsername), pageable);
    }

    @Override
    public Message save(Message message) {
        return messages.put(idCounter++, message);
    }

    private Page<Message> findByPredicate(Predicate<Message> predicate, Pageable pageable) {
        var filteredMessages = messages.values().stream().filter(predicate).collect(Collectors.toList());
        return new PageImpl<>(filteredMessages, pageable, filteredMessages.size());
    }
}
