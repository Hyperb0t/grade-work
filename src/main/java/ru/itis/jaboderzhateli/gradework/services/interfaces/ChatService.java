package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.Channel;
import ru.itis.jaboderzhateli.gradework.models.Message;
import ru.itis.jaboderzhateli.gradework.models.User;

import java.util.List;
import java.util.Optional;

public interface ChatService {

    Channel createChannel(String name);

    void addUsersToChannel(Long channelId, Long... userIds);

    Channel createChannelForUsers(String name, Long... userIds);

    Message saveMessage(Long channelId, Long userAuthorId, String content);

    List<Message> getMessagesForChannel(Long channelId);

    Optional<Channel> getChannel(Long id);

    Optional<Message> getMessage(Long id);

    List<Channel> checkIfChannelExistsForUsers(Long userId1, Long userId2);

    boolean checkIfUserBelongsToChat(Long userId, Long channelId);

    List<User> getUsersForChannel(Long channelId);

    List<Channel> getChannelsByUserId(Long userId);
}
