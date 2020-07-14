package ru.itis.jaboderzhateli.gradework.dto.poijiDto;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.jaboderzhateli.gradework.dto.StudentDto;
import ru.itis.jaboderzhateli.gradework.models.Student;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentPoijiDto{

    @ExcelCellName("Год начала обучения")
    private Integer yearStart;
    @ExcelCellName("Логин")
    private String login;
    @ExcelCellName("Год окончания обучения")
    private Integer yearGraduate;
    @ExcelCellName("Курс")
    private Integer course;
    @ExcelCellName("Биография")
    private String bio;
    @ExcelCellName("Имя")
    private String name;
    @ExcelCellName("Фамилия")
    private String surname;
    @ExcelCellName("Группа")
    private String group;
    @ExcelCellName("Email")
    private String email;
    @ExcelCellName("Телефон")
    private String phone;
    @ExcelCellName("Институт")
    private String institute;

    public static StudentPoijiDto from(StudentDto student) {
        return StudentPoijiDto.builder()
//                .login(student.getLogin())
                .yearStart(student.getYearStart().intValue())
                .yearGraduate(student.getYearGraduate().intValue())
                .course(student.getCourse().intValue())
                .bio(student.getBio())
//                .name(student.getName())
//                .surname(student.getSurname())
                .group(student.getGroup())
//                .email(student.getEmail())
//                .phone(student.getPhone())
                .institute(student.getInstitute().getName())
                .build();
    }
}