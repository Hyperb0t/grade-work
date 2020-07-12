package ru.itis.jaboderzhateli.gradework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.jaboderzhateli.gradework.models.Role;
import ru.itis.jaboderzhateli.gradework.models.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserDto {

    private String login;
    private Role role;

    public static UserDto from(User user) {
        return UserDto.builder()
                .login(user.getLogin())
                .role(user.getRole())
                .build();
    }

}
