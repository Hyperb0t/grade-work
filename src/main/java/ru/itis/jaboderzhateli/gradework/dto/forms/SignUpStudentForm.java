package ru.itis.jaboderzhateli.gradework.dto.forms;

import lombok.Data;
import ru.itis.jaboderzhateli.gradework.utils.FieldMatch;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@FieldMatch(first = "password", second = "passwordRepeat")
public class SignUpStudentForm {

    @NotBlank(message = "Данное поле не может быть пустым")
    private String login;
    @Size(min = 6, max = 40, message = "Минимальная длина пароля 6 символов")
    private String password;
    private String passwordRepeat;
    private String name;
    private String surname;
    private String middleName;

    private String faculty;
    private String institute;
    private String group;
    private String link;
    private Double average;
    private String phone;
}
