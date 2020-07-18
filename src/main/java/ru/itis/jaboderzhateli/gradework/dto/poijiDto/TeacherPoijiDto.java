package ru.itis.jaboderzhateli.gradework.dto.poijiDto;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.jaboderzhateli.gradework.dto.StudentDto;
import ru.itis.jaboderzhateli.gradework.dto.TeacherDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherPoijiDto{

    @ExcelCellName("Имя")
    private String name;
    @ExcelCellName("Фамилия")
    private String surname;
    @ExcelCellName("Отчество")
    private String middleName;
    @ExcelCellName("Почта")
    private String email;
    @ExcelCellName("Телефон")
    private String phone;
    @ExcelCellName("Логин")
    private String login;
    private String password;
    @ExcelCellName("Должность")
    private String position;
    @ExcelCellName("Ссылка")
    private String link;
    @ExcelCellName("Стаж работы")
    private Integer experience;
    @ExcelCellName("Институт")
    private String institute;

    public static TeacherPoijiDto from(TeacherDto teacherDto) {
        return TeacherPoijiDto.builder()
                .login(teacherDto.getLogin())
                .name(teacherDto.getName())
                .surname(teacherDto.getSurname())
                .email(teacherDto.getEmail())
                .phone(teacherDto.getPhone())
                .build();
    }

}
