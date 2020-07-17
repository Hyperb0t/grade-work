package ru.itis.jaboderzhateli.gradework.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.jaboderzhateli.gradework.utils.FieldMatch;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldMatch(first = "password", second = "passwordRepeat")
public class SignUpEmployerForm {

    @NotBlank(message = "Данное поле не может быть пустым")
    private String login;
    @Size(min = 6, max = 40, message = "Минимальная длина пароля 6 символов")
    private String password;
    private String passwordRepeat;
    @NotBlank(message = "Поле \"наименование организации\" не может быть пустым")
    private String organisationName;
    private String psrn;
    private String name;
    private String surname;
    private String middleName;
    private String phone;
    @Email(message = "Введите корректную почту")
    private String email;
    private List<String> link;
    @AssertTrue(message = "Вы должны принять согласие на обработку, чтобы продолжить")
    private boolean agreement;

}
