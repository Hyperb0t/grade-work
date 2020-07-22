package ru.itis.jaboderzhateli.gradework.services.implementations;

import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.User;
import ru.itis.jaboderzhateli.gradework.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(Long id) throws IllegalArgumentException {
        return null;
    }

    @Override
    public User getUser(String name) throws IllegalArgumentException{
        return null;
    }
}
