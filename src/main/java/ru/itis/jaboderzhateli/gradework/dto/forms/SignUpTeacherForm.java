package ru.itis.jaboderzhateli.gradework.dto.forms;

import lombok.*;
import ru.itis.jaboderzhateli.gradework.utils.FieldMatch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldMatch(first = "password", second = "passwordRepeat")
public class SignUpTeacherForm {

    @NotBlank
    private String login;
    @Size(min=8, max = 40)
    private String password;
    @Size(min=8, max = 40)
    private String passwordRepeat;
    @NotBlank
    private String name;
    private String surname;
    private String middleName;
    private Byte experience;
    @NotBlank
    private String institute;
    @NotBlank
    private String position;
    private String link;
    private List<String> competence;

}
