package ru.itis.jaboderzhateli.gradework.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.itis.jaboderzhateli.gradework.utils.FieldMatch;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldMatch(first = "password", second = "passwordRepeat")
public class SignUpStudentForm {

    @NotBlank(message = "Данное поле не может быть пустым")
    private String login;
    @Size(min = 6, max = 40, message = "Минимальная длина пароля 6 символов")
    private String password;
    private String passwordRepeat;
    @NotBlank
    private String name;
    private String surname;
    private String middleName;
    @NotBlank
    private String faculty;
    @NotBlank
    private String institute;
    @NotBlank
    private String group;
    private String link;
    private String phone;
    private Short yearStart;
    private Short yearGraduate;
    private Byte average;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
}
