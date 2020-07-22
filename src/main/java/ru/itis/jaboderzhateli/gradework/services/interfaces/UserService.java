package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.User;

public interface UserService {

    User getUser(Long id) throws IllegalArgumentException;

    User getUser(String name) throws IllegalArgumentException;
}
