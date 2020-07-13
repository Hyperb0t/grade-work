package ru.itis.jaboderzhateli.gradework.dto.poijiDto;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.jaboderzhateli.gradework.dto.StudentDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherPoijiDto {

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

    public static StudentPoijiDto from(StudentDto student) {
        return StudentPoijiDto.builder()
                .login(student.getLogin())
                .yearStart(student.getYearStart().intValue())
                .yearGraduate(student.getYearGraduate().intValue())
                .course(student.getCourse().intValue())
                .bio(student.getBio())
                .name(student.getName())
                .surname(student.getSurname())
                .group(student.getGroup())
                .email(student.getEmail())
                .phone(student.getPhone())
                .institute(student.getInstitute().getName())
                .build();
    }

}
