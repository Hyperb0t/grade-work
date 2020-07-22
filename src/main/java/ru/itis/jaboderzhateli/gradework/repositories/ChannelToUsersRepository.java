package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.jaboderzhateli.gradework.models.ChannelToUser;
import ru.itis.jaboderzhateli.gradework.models.ChannelToUserId;

import java.util.List;

public interface ChannelToUsersRepository extends JpaRepository<ChannelToUser, ChannelToUserId> {
    List<ChannelToUser> findByUser_Id(Long userId);
    List<ChannelToUser> findByChannel_Id(Long channelId);
    boolean existsByUser_IdAndChannel_Id(Long userId, Long channelId);
}
