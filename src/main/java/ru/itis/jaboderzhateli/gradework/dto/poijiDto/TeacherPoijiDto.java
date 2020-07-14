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
    @ExcelCellName("Email")
    private String email;
    @ExcelCellName("Телефон")
    private String phone;
    @ExcelCellName("Логин")
    private String login;

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