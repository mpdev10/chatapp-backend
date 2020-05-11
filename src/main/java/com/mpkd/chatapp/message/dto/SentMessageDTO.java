package com.mpkd.chatapp.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class SentMessageDTO {

    private String receiverUsername;
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SentMessageDTO that = (SentMessageDTO) o;
        return receiverUsername.equals(that.receiverUsername) &&
                content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiverUsername, content);
    }

    @Override
    public String toString() {
        return "SentMessageDTO{" +
                "receiverUsername='" + receiverUsername + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
