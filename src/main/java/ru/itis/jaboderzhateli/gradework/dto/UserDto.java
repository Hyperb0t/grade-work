package ru.itis.jaboderzhateli.gradework.dto;

import lombok.Data;
import ru.itis.jaboderzhateli.gradework.models.Role;

@Data
public abstract class UserDto {

    private String login;

    private Role role;

}
