package com.mpkd.chatapp.message;

import com.mpkd.chatapp.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Message extends BaseEntity {

    private String senderUsername;
    private String receiverUsername;
    private String content;

}
