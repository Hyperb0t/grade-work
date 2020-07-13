package ru.itis.jaboderzhateli.gradework.dto.forms;

import lombok.Builder;
import lombok.Data;
import ru.itis.jaboderzhateli.gradework.utils.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@FieldMatch(first = "password", second = "passwordRepeat")
public class SignUpEmployerForm {

    @NotBlank(message = "Данное поле не может быть пустым")
    private String login;
    @Size(min = 6, max = 40, message = "Минимальная длина пароля 6 символов")
    private String password;
    private String passwordRepeat;

    private String bio;
    private String juristicInfo;
    @NotBlank(message = "Поле \"наименование организации\" не может быть пустым")
    private String companyName;
    @Email(message = "Введите корректную почту")
    private String email;
    private String phone;

}
