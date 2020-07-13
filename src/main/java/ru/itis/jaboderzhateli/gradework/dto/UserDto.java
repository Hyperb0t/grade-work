package ru.itis.jaboderzhateli.gradework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.jaboderzhateli.gradework.models.Role;
import ru.itis.jaboderzhateli.gradework.models.User;

@Data
public abstract class UserDto {

    private String login;
    private String password;

    private Role role;

}
