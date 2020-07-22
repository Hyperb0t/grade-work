package ru.itis.jaboderzhateli.gradework.services.implementations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.Channel;
import ru.itis.jaboderzhateli.gradework.models.ChannelToUser;
import ru.itis.jaboderzhateli.gradework.models.Message;
import ru.itis.jaboderzhateli.gradework.models.User;
import ru.itis.jaboderzhateli.gradework.repositories.ChannelToUsersRepository;
import ru.itis.jaboderzhateli.gradework.repositories.ChannelsRepository;
import ru.itis.jaboderzhateli.gradework.repositories.MessagesRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ChatService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChannelsRepository channelsRepository;

    @Autowired
    private ChannelToUsersRepository channelToUsersRepository;

    @Autowired
    private MessagesRepository messagesRepository;


    @Override
    public Channel createChannel(String name) {
        return channelsRepository.save(Channel.builder().name(name).build());
    }

    @Override
    public void addUsersToChannel(Long channelId, Long... userIds) {
        for(Long userId : userIds) {
            channelToUsersRepository.save(ChannelToUser.builder()
                    .channel(Channel.builder().id(channelId).build())
                    .user(User.builder().id(userId).build()).build());
        }
    }

    @Override
    public Channel createChannelForUsers(String name, Long... userIds) {
        Channel channel = createChannel(name);
        addUsersToChannel(channel.getId(), userIds);
        return channel;
    }

    @Override
    public Message saveMessage(Long channelId, Long userAuthorId, String content) {
        return messagesRepository.save(Message.builder()
                .author(User.builder().id(userAuthorId).build())
                .channel(Channel.builder().id(channelId).build())
                .content(content).build());
    }

    @Override
    public List<Message> getMessagesForChannel(Long channelId) {
        return messagesRepository.findByChannel_Id(channelId);
    }

    @Override
    public Optional<Channel> getChannel(Long id) {
        return channelsRepository.findById(id);
    }

    @Override
    public Optional<Message> getMessage(Long id) {
        return messagesRepository.findById(id);
    }

    @Override
    public List<Channel> checkIfChannelExistsForUsers(Long userId1, Long userId2) {
        List<ChannelToUser> channels1 = channelToUsersRepository.findByUser_Id(userId1);
        List<ChannelToUser> channels2 = channelToUsersRepository.findByUser_Id(userId2);
        List<Channel> suitableChannels = new ArrayList<>();
        for(ChannelToUser channel1 : channels1) {
            for(ChannelToUser channel2 : channels2) {
                if(channel1.getChannel().getId().equals(channel2.getChannel().getId())) {
                    suitableChannels.add(channel1.getChannel());
                }
            }
        }
        return suitableChannels;
    }

    @Override
    public boolean checkIfUserBelongsToChat(Long userId, Long channelId) {
        return channelToUsersRepository.existsByUser_IdAndChannel_Id(userId, channelId);
    }

    @Override
    public List<User> getUsersForChannel(Long channelId) {
        List<ChannelToUser> channelToUsers = channelToUsersRepository.findByChannel_Id(channelId);
        List<User> users = new ArrayList<>();
        for(ChannelToUser channelToUser : channelToUsers) {
            users.add(channelToUser.getUser());
        }
        return users;
    }

    @Override
    public List<Channel> getChannelsByUserId(Long userId) {
        List<ChannelToUser> channelToUsers = channelToUsersRepository.findByUser_Id(userId);
        List<Channel> channels = new ArrayList<>();
        for(ChannelToUser channelToUser : channelToUsers) {
            channels.add(channelToUser.getChannel());
        }
        return channels;
    }

//    @Override
//    public Dto getLastChannelMessagesForUserId(Long userId) {
//        return new WebDto(Status.MESSAGE_LOAD_LAST_FOR_USER_ID_CHANNELS_SUCCESS, "messages", messagesRepository.findLastMessagesForChannelsByUserId(userId));
//    }
}
