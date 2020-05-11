package com.mpkd.chatapp.message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;


interface MessageRepository extends Repository<Message, Long> {

    Page<Message> findAllByReceiverUsername(String receiverUsername, Pageable pageable);

    Page<Message> findAllBySenderUsername(String senderUsername, Pageable pageable);

    Message save(Message message);
}
