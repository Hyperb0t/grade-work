package ru.itis.jaboderzhateli.gradework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelToUserId implements Serializable {
    private Long channel;
    private Long user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelToUserId that = (ChannelToUserId) o;
        return channel.equals(that.channel) &&
                user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel, user);
    }
}
