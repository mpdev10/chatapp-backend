package com.mpkd.chatapp.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class ReceivedMessageDTO {

    private String senderUsername;
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReceivedMessageDTO that = (ReceivedMessageDTO) o;
        return senderUsername.equals(that.senderUsername) &&
                content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderUsername, content);
    }

    @Override
    public String toString() {
        return "ReceivedMessageDTO{" +
                "senderUsername='" + senderUsername + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
