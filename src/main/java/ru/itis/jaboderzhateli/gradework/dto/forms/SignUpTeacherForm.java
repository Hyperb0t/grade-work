package ru.itis.jaboderzhateli.gradework.dto.forms;

import lombok.Data;
import ru.itis.jaboderzhateli.gradework.utils.FieldMatch;

import java.util.List;

@Data
@FieldMatch(first = "password", second = "passwordRepeat")
public class SignUpTeacherForm {

    private String login;
    private String password;
    private String passwordRepeat;
    private String name;
    private String surname;
    private String middleName;
    private Byte experience;
    private String institute;
    private String position;
    private String link;
    private List<String> competence;

}
