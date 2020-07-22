package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.jaboderzhateli.gradework.models.Channel;

import java.util.List;
import java.util.Optional;

public interface ChannelsRepository extends JpaRepository<Channel, Long> {
}
