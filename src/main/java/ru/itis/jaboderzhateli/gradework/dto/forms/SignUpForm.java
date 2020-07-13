package ru.itis.jaboderzhateli.gradework.dto.forms;

import lombok.Builder;
import lombok.Data;
import ru.itis.jaboderzhateli.gradework.utils.FieldMatch;

@Data
@FieldMatch(first = "password", second = "passwordRepeat")
public abstract class SignUpForm {

    private String login;
    private String password;
    private String passwordRepeat;

}
